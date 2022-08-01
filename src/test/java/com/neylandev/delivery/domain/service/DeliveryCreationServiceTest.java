package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.enums.DeliveryStatus;
import com.neylandev.delivery.domain.model.Delivery;
import com.neylandev.delivery.domain.repository.ClientRepository;
import com.neylandev.delivery.domain.repository.DeliveryRepository;
import com.neylandev.delivery.infrastructure.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static com.neylandev.delivery.DataForTests.INVALID_CLIENT_ID;
import static com.neylandev.delivery.DataForTests.INVALID_DELIVERY_ID;
import static com.neylandev.delivery.DataForTests.VALID_CLIENT_ID;
import static com.neylandev.delivery.DataForTests.clientValid;
import static com.neylandev.delivery.DataForTests.deliveryRequestDtoValid;
import static com.neylandev.delivery.DataForTests.deliveryValid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryCreationServiceTest {

    @InjectMocks
    private DeliveryCreationService deliveryCreationService;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private FindDeliveryService findDeliveryService;

    @Test
    void shouldSaveDelivery() {
        var client = clientValid();

        var deliveryRequestDto = deliveryRequestDtoValid();

        ArgumentCaptor<Delivery> deliveryArgumentCaptor = ArgumentCaptor.forClass(Delivery.class);

        when(clientRepository.findById(VALID_CLIENT_ID)).thenReturn(Optional.of(client));
        when(deliveryRepository.save(any(Delivery.class))).thenReturn(deliveryValid());

        var deliveryResponseDto = deliveryCreationService.save(deliveryRequestDto);

        verify(deliveryRepository).save(deliveryArgumentCaptor.capture());
        var deliveryArgumentCaptorValue = deliveryArgumentCaptor.getValue();
        assertEquals(client.getTelephone(), deliveryArgumentCaptorValue.getClient().getTelephone());
        assertNotNull(deliveryArgumentCaptorValue.getOrderedDate());
        assertNotNull(deliveryResponseDto);
        assertEquals(DeliveryStatus.PENDING, deliveryArgumentCaptorValue.getDeliveryStatus());
    }

    @Test
    void shouldThrowBusinessException_whenClientIdNotFound() {
        var deliveryRequestDto = deliveryRequestDtoValid();
        deliveryRequestDto.setClientId(INVALID_CLIENT_ID);

        when(clientRepository.findById(INVALID_CLIENT_ID)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class,
                () -> deliveryCreationService.save(deliveryRequestDto),
                DataForBusinessException.CLIENT_DELIVERY_NOT_FOUND.getMessage());


    }

    @Test
    void shouldFindAllDeliveries() {
        var delivery = deliveryValid();
        when(deliveryRepository.findAll()).thenReturn(Collections.singletonList(delivery));

        var deliveryResponseDtoList = deliveryCreationService.findAll();

        assertEquals(delivery.getId(), deliveryResponseDtoList.stream().iterator().next().getId());
        assertEquals(delivery.getClient().getId(), deliveryResponseDtoList.stream().iterator().next().getClientId());
        assertEquals(delivery.getDeliveryStatus(), deliveryResponseDtoList.stream().iterator().next().getDeliveryStatus());
    }

    @Test
    void shouldFindDeliveryById() {
        var delivery = deliveryValid();

        when(findDeliveryService.find(delivery.getId())).thenReturn(delivery);

        var deliveryResponseDtoList = deliveryCreationService.findById(delivery.getId());

        assertEquals(delivery.getId(), deliveryResponseDtoList.getId());
        assertEquals(delivery.getClient().getId(), deliveryResponseDtoList.getClientId());
        assertEquals(delivery.getDeliveryStatus(), deliveryResponseDtoList.getDeliveryStatus());
    }

    @Test
    void shouldThrowBusinessException_whenFindDeliveryById() {
        when(findDeliveryService.find(INVALID_DELIVERY_ID))
                .thenThrow(DataForBusinessException.DELIVERY_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_DELIVERY_ID)));

        assertThrows(BusinessException.class, () -> deliveryCreationService.findById(INVALID_DELIVERY_ID),
                DataForBusinessException.DELIVERY_NOT_FOUND.getMessage());
    }


}