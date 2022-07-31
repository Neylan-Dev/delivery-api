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
        DeliveryEmailDto deliveryEmailDto = delivery.getDeliveryStatus().equals(DeliveryStatus.FINALIZED) ? getDeliveryEmailDtoFinalized(delivery) : getDeliveryEmailDtoCanceled(delivery);
        deliverySendEmailProducer.send(deliveryEmailDto);
    }

    private DeliveryEmailDto getDeliveryEmailDtoCanceled(Delivery delivery) {
        return DeliveryEmailDto.builder()
                .clientEmail(delivery.getClient().getEmail())
                .subject("O envio do produto foi cancelado")
                .body(String.format("O produto de %s não pode ser enviado", delivery.getClient().getName()))
                .build();
    }

    private DeliveryEmailDto getDeliveryEmailDtoFinalized(Delivery delivery) {
        return DeliveryEmailDto.builder()
                .clientEmail(delivery.getClient().getEmail())
                .subject("Produto recebido com sucesso")
                .body(String.format("O produto de %s foi recebido por %s", delivery.getClient().getName(), delivery.getRecipient().getName()))
                .build();
    }
}
