package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.dto.OrderEmailDto;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.enums.OrderStatus;
import com.neylandev.delivery.domain.model.Order;
import com.neylandev.delivery.domain.producer.OrderSendEmailProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderSendEmailService {

    private final OrderSendEmailProducer orderSendEmailProducer;

    public void sendEmail(Order order) {
        OrderEmailDto orderEmailDto = null;
        if (order.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            orderEmailDto = getOrderEmailDtoDelivered(order);
        } else if (order.getOrderStatus().equals(OrderStatus.CANCELLED)) {
            orderEmailDto = getOrderEmailDtoCanceled(order);
        } else {
            throw DataForBusinessException.EMAIL_CANNOT_BE_SEND.asBusinessException();
        }
        orderSendEmailProducer.send(orderEmailDto);
    }

    private OrderEmailDto getOrderEmailDtoCanceled(Order order) {
        return OrderEmailDto.builder()
                .clientEmail(order.getClient().getEmail())
                .subject("O envio do produto foi cancelado")
                .body(String.format("O produto de %s não pode ser enviado", order.getClient().getName()))
                .build();
    }

    private OrderEmailDto getOrderEmailDtoDelivered(Order order) {
        return OrderEmailDto.builder()
                .clientEmail(order.getClient().getEmail())
                .subject("Produto recebido com sucesso")
                .body(String.format("O produto de %s foi recebido por %s", order.getClient().getName(), order.getRecipient().getName()))
                .build();
    }
}
