package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.model.Delivery;
import com.neylandev.delivery.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderCompletionService {

    private final OrderRepository orderRepository;
    private final FindDeliveryService findDeliveryService;
    private final DeliverySendEmailService deliverySendEmailService;

    @Transactional
    public void complete(Long deliveryId) {
        Delivery delivery = findDeliveryService.find(deliveryId);
        delivery.complete();
        deliverySendEmailService.sendEmail(orderRepository.save(delivery));
    }

    @Transactional
    public void cancel(Long deliveryId) {
        Delivery delivery = findDeliveryService.find(deliveryId);
        delivery.cancel();
        deliverySendEmailService.sendEmail(orderRepository.save(delivery));
    }
}
