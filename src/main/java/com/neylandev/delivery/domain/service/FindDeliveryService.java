package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.model.Delivery;
import com.neylandev.delivery.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindDeliveryService {

    private final OrderRepository orderRepository;

    public Delivery find(Long deliveryId) {
        return orderRepository.findById(deliveryId)
                .orElseThrow(() -> DataForBusinessException.DELIVERY_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(deliveryId)));
    }

}
