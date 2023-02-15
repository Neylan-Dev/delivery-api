package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.repository.OrderRepository;
import com.neylandev.delivery.infrastructure.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.neylandev.delivery.DataForTests.INVALID_DELIVERY_ID;
import static com.neylandev.delivery.DataForTests.deliveryValid;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindOrderServiceTest {

    @InjectMocks
    private FindDeliveryService findDeliveryService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void shouldFindDelivery() {
        var delivery = deliveryValid();

        when(orderRepository.findById(delivery.getId())).thenReturn(Optional.of(delivery));

        var deliveryReturned = findDeliveryService.find(delivery.getId());

        assertEquals(delivery, deliveryReturned);
    }

    @Test
    void shouldThrowBusinessException_whenDeliveryNotFound() {

        when(orderRepository.findById(INVALID_DELIVERY_ID)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class,
                () -> findDeliveryService.find(INVALID_DELIVERY_ID),
                DataForBusinessException.DELIVERY_NOT_FOUND.getMessage());
    }
}