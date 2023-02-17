package com.neylandev.delivery.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCompletionService {

//    private final OrderRepository orderRepository;
//    private final FindDeliveryService findDeliveryService;
//    private final OrderSendEmailService orderSendEmailService;
//
//    @Transactional
//    public void complete(Long deliveryId) {
//        Delivery delivery = findDeliveryService.find(deliveryId);
//        delivery.complete();
//        orderSendEmailService.sendEmail(orderRepository.save(delivery));
//    }
//
//    @Transactional
//    public void cancel(Long deliveryId) {
//        Delivery delivery = findDeliveryService.find(deliveryId);
//        delivery.cancel();
//        orderSendEmailService.sendEmail(orderRepository.save(delivery));
//    }
}
