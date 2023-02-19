package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.application.request.PaymentRequestDto;
import com.neylandev.delivery.application.response.PaymentResponseDto;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.enums.PaymentStatus;
import com.neylandev.delivery.domain.enums.PaymentType;
import com.neylandev.delivery.domain.model.Order;
import com.neylandev.delivery.domain.model.Payment;
import com.neylandev.delivery.domain.repository.PaymentRepository;
import com.neylandev.delivery.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public void save(Order order, List<PaymentRequestDto> paymentRequestDtos) {
        this.validate(paymentRequestDtos);
        paymentRequestDtos.forEach(paymentRequestDto -> save(order, paymentRequestDto));
    }


    public void save(Order order, PaymentRequestDto paymentRequestDto) {
        var payment = ParseObjects.paymentRequestDtoToPayment(paymentRequestDto);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setOrder(order);
        paymentRepository.save(payment);
    }


    public PaymentResponseDto findById(Long paymentId) {
        return ParseObjects.paymentToPaymentResponseDto(paymentRepository.findById(paymentId)
                .orElseThrow(() -> DataForBusinessException.PAYMENT_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(paymentId))));
    }

    public void pay(Order order, List<PaymentRequestDto> paymentRequestDtos) {
        this.validate(paymentRequestDtos);
        pay(order.getPayments(), paymentRequestDtos);
    }

    public PaymentResponseDto pay(List<Payment> payments, List<PaymentRequestDto> paymentRequestDtos) {
        payments.sort(Comparator.comparing(o -> o.getPaymentType().toString()));
        paymentRequestDtos.sort(Comparator.comparing(o -> o.getPaymentType().toString()));
        if (payments.size() == paymentRequestDtos.size()) {
            for (int i = 0; i < paymentRequestDtos.size(); i++) {
                PaymentRequestDto paymentRequestDto = paymentRequestDtos.get(i);
                Payment payment = payments.get(i);
                if (paymentRequestDto.getPaymentType().equals(payment.getPaymentType())) {
                    if (paymentRequestDto.getAmount().equals(payment.getAmount())) {
                        payment.setFlagPaid(true);
                        payment.setPaymentStatus(PaymentStatus.COMPLETED);
                        //ENVIAR EMAIL
                    } else {
                        throw DataForBusinessException.PAYMENT_INCORRECT.asBusinessException();
                    }
                } else {
                    throw DataForBusinessException.PAYMENT_INCORRECT.asBusinessException();
                }
            }
        }
        throw DataForBusinessException.PAYMENT_INCORRECT.asBusinessException();
    }

    public void refund(List<Payment> payments) {
        payments.forEach(payment -> {
            refund(payment);
        });
    }

    private void refund(Payment payment) {
        if (payment.getPaymentStatus().equals(PaymentStatus.COMPLETED)) {
            switch (payment.getPaymentType()) {
                case CREDIT_CARD:
                case DEBIT_CARD:
                    //PAGAMENTO SERÁ DEVOLVIDO AO CARTÃO
                    //ENVIAR EMAIL
                    break;
                case PIX:
                case BANK_BILL:
                    if (Objects.nonNull(payment.getOrder().getClient().getAccount())) {
                        //PAGAMENTO SERÁ DEVOLVIDO A CONTA
                        //ENVIAR EMAIL
                    } else {
                        throw DataForBusinessException.REFUND_CANNOT_BE_DONE.asBusinessException();
                    }
            }
        }
        payment.setPaymentStatus(PaymentStatus.REFUNDED);
        paymentRepository.save(payment);
    }

    public List<PaymentResponseDto> findAll() {
        return ParseObjects.listPaymentToListPaymentResponseDto(paymentRepository.findAll());
    }

    private void validate(List<PaymentRequestDto> paymentRequestDtos) {
        paymentRequestDtos.forEach(this::validate);
    }

    private void validate(PaymentRequestDto paymentRequestDto) {
        if (paymentRequestDto.getPaymentType().equals(PaymentType.CREDIT_CARD) || paymentRequestDto.getPaymentType().equals(PaymentType.DEBIT_CARD)) {
            if (paymentRequestDto.getCardName() == null || paymentRequestDto.getCardName().isEmpty()) {
                throw DataForBusinessException.PAYMENT_CANNOT_BE_SAVED.asBusinessException();
            } else if (paymentRequestDto.getCardNumber() == null || paymentRequestDto.getCardNumber().isEmpty()) {
                throw DataForBusinessException.PAYMENT_CANNOT_BE_SAVED.asBusinessException();
            } else if (paymentRequestDto.getCvv() == null || paymentRequestDto.getCvv().isEmpty()) {
                throw DataForBusinessException.PAYMENT_CANNOT_BE_SAVED.asBusinessException();
            }
        } else if (paymentRequestDto.getPaymentType().equals(PaymentType.BANK_BILL)) {
            if (paymentRequestDto.getBarCode() == null || paymentRequestDto.getBarCode().isEmpty()) {
                throw DataForBusinessException.PAYMENT_CANNOT_BE_SAVED.asBusinessException();
            }
        } else if (paymentRequestDto.getPaymentType().equals(PaymentType.PIX)) {
            if (paymentRequestDto.getPixKey() == null || paymentRequestDto.getPixKey().isEmpty()) {
                throw DataForBusinessException.PAYMENT_CANNOT_BE_SAVED.asBusinessException();
            }
        }
    }

    public void cancel(List<Payment> payments) {
        payments.forEach(payment -> payment.setPaymentStatus(PaymentStatus.REFUNDED));
    }
}
