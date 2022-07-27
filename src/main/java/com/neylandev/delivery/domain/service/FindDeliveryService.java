package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.model.Delivery;
import com.neylandev.delivery.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindDeliveryService {

    private final DeliveryRepository deliveryRepository;

    public Delivery find(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> DataForBusinessException.DELIVERY_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(deliveryId)));
    }

}
