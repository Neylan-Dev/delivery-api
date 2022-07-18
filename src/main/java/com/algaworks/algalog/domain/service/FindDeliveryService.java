package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.enums.DataForBusinessException;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
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
