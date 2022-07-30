package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.dto.DeliveryEmailDto;
import com.neylandev.delivery.domain.enums.DeliveryStatus;
import com.neylandev.delivery.domain.model.Delivery;
import com.neylandev.delivery.domain.producer.DeliverySendEmailProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliverySendEmailService {

    private final DeliverySendEmailProducer deliverySendEmailProducer;

    public void sendEmail(Delivery delivery){
        DeliveryEmailDto deliveryEmailDto = DeliveryEmailDto.builder()
                .clientEmail(delivery.getClient().getEmail())
                .clientName(delivery.getClient().getName())
                .bodyMessage(delivery.getDeliveryStatus().equals(DeliveryStatus.FINALIZED)
                        ? "O produto foi recebido com sucesso"
                        : "O o produto não pode ser enviado, portanto a venda será cancelada")
                .build();
        deliverySendEmailProducer.send(deliveryEmailDto);
    }
}
