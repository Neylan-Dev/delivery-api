package com.neylandev.delivery.application.route;

import com.neylandev.delivery.domain.dto.DeliveryEmailDto;
import com.neylandev.delivery.domain.utils.Constants;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQRoute extends RouteBuilder {

    @Override
    public void configure() {

        from(Constants.DIRECT_SEND_EMAIL)
                .routeId(Constants.DIRECT_SEND_EMAIL)
                .marshal()
                .json(JsonLibrary.Jackson, DeliveryEmailDto.class)
                .to("{{to.delivery.email}}")
                .log("ENVIADO PARA FILA DE DELIVERY EMAIL - RABBITMQ");

    }
}
