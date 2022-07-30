package com.neylandev.delivery.application.route;

import com.neylandev.delivery.domain.dto.DeliveryEmailDto;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQRoute extends RouteBuilder {

    @Value("${rabbitmq.camel.directSendEmail}")
    private String directSendEmail;

    @Override
    public void configure() {

        from(directSendEmail)
                .routeId(directSendEmail)
                .marshal()
                .json(JsonLibrary.Jackson, DeliveryEmailDto.class)
                .to("{{to.delivery.email}}")
                .log("ENVIADO PARA FILA DE DELIVERY EMAIL - RABBITMQ");

    }
}
