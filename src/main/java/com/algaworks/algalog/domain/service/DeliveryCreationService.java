package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.application.request.DeliveryRequestDto;
import com.algaworks.algalog.application.response.DeliveryResponseDto;
import com.algaworks.algalog.domain.enums.DataForBusinessException;
import com.algaworks.algalog.domain.enums.DeliveryStatus;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import com.algaworks.algalog.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

import static com.algaworks.algalog.domain.utils.ParseObjects.deliveryRequestDtoToDelivery;
import static com.algaworks.algalog.domain.utils.ParseObjects.deliveryToDeliveryResponseDto;
import static com.algaworks.algalog.domain.utils.ParseObjects.listDeliveryToListDeliveryResponseDto;

@Service
@RequiredArgsConstructor
public class DeliveryCreationService {

    private final DeliveryRepository deliveryRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public DeliveryResponseDto save(DeliveryRequestDto deliveryRequestDto) {
        Client client = findClientById(deliveryRequestDto);
        var delivery = deliveryRequestDtoToDelivery(deliveryRequestDto);
        delivery.setDeliveryStatus(DeliveryStatus.PENDING);
        delivery.setOrderedDate(OffsetDateTime.now());
        delivery.setClient(client);
        return deliveryToDeliveryResponseDto(deliveryRepository.save(delivery));
    }

    private Client findClientById(DeliveryRequestDto deliveryDto) {
        return clientRepository.findById(deliveryDto.getClientId())
                .orElseThrow(() -> DataForBusinessException.CLIENT_DELIVERY_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(deliveryDto.getClientId())));
    }

    public List<DeliveryResponseDto> findAll() {
        return listDeliveryToListDeliveryResponseDto(deliveryRepository.findAll());
    }

    public DeliveryResponseDto findById(Long deliveryId) {
        return deliveryRepository.findById(deliveryId).map(ParseObjects::deliveryToDeliveryResponseDto)
                .orElseThrow(() -> DataForBusinessException.DELIVERY_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(deliveryId)));
    }
}
