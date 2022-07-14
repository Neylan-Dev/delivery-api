package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.DeliveryDto;
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

import static com.algaworks.algalog.domain.utils.ParseObjects.deliveryDtoToDelivery;
import static com.algaworks.algalog.domain.utils.ParseObjects.deliveryToDeliveryDto;
import static com.algaworks.algalog.domain.utils.ParseObjects.listDeliveryToListDeliveryDto;

@Service
@RequiredArgsConstructor
public class DeliveryCreationService {

    private final DeliveryRepository deliveryRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public DeliveryDto save(DeliveryDto deliveryDto) {
        deliveryDto.setDeliveryStatus(DeliveryStatus.PENDING);
        deliveryDto.setOrderedDate(OffsetDateTime.now());
        Client client = findClientById(deliveryDto);
        var delivery = deliveryDtoToDelivery(deliveryDto);
        delivery.setClient(client);
        return deliveryToDeliveryDto(deliveryRepository.save(delivery));
    }

    private Client findClientById(DeliveryDto deliveryDto) {
        return clientRepository.findById(deliveryDto.getClientId())
                .orElseThrow(() -> DataForBusinessException.CLIENT_DELIVERY_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(deliveryDto.getClientId())));
    }

    public List<DeliveryDto> findAll() {
        return listDeliveryToListDeliveryDto(deliveryRepository.findAll());
    }

    public DeliveryDto findById(Long deliveryId) {
        return deliveryRepository.findById(deliveryId).map(ParseObjects::deliveryToDeliveryDto)
                .orElseThrow(() -> DataForBusinessException.DELIVERY_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(deliveryId)));
    }
}
