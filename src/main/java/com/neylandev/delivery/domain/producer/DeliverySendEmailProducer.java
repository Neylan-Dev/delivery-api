package com.neylandev.delivery.domain.producer;

import lombok.RequiredArgsConstructor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliverySendEmailProducer {

    @Produce(uri = "${rabbitmq.camel.directSendEmail}")
    private final ProducerTemplate producerTemplate;

    @Async
    public void send(){
        producerTemplate.sendBody(null);
    }
}
