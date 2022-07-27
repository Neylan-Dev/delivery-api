package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.application.request.DeliveryRequestDto;
import com.neylandev.delivery.application.response.DeliveryResponseDto;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.enums.DeliveryStatus;
import com.neylandev.delivery.domain.model.Client;
import com.neylandev.delivery.domain.repository.ClientRepository;
import com.neylandev.delivery.domain.repository.DeliveryRepository;
import com.neylandev.delivery.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryCreationService {

    private final DeliveryRepository deliveryRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public DeliveryResponseDto save(DeliveryRequestDto deliveryRequestDto) {
        Client client = findClientById(deliveryRequestDto);
        var delivery = ParseObjects.deliveryRequestDtoToDelivery(deliveryRequestDto);
        delivery.setDeliveryStatus(DeliveryStatus.PENDING);
        delivery.setOrderedDate(OffsetDateTime.now());
        delivery.setClient(client);
        return ParseObjects.deliveryToDeliveryResponseDto(deliveryRepository.save(delivery));
    }

    private Client findClientById(DeliveryRequestDto deliveryDto) {
        return clientRepository.findById(deliveryDto.getClientId())
                .orElseThrow(() -> DataForBusinessException.CLIENT_DELIVERY_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(deliveryDto.getClientId())));
    }

    public List<DeliveryResponseDto> findAll() {
        return ParseObjects.listDeliveryToListDeliveryResponseDto(deliveryRepository.findAll());
    }

    public DeliveryResponseDto findById(Long deliveryId) {
        return deliveryRepository.findById(deliveryId).map(ParseObjects::deliveryToDeliveryResponseDto)
                .orElseThrow(() -> DataForBusinessException.DELIVERY_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(deliveryId)));
    }
}
