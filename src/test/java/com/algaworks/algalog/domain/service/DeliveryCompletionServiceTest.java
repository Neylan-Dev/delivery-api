package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.infrastructure.exception.BusinessException;
import com.algaworks.algalog.domain.enums.DataForBusinessException;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.algaworks.algalog.DataForTests.INVALID_DELIVERY_ID;
import static com.algaworks.algalog.DataForTests.deliveryValid;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryCompletionServiceTest {

    @InjectMocks
    private DeliveryCompletionService deliveryCompletionService;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private FindDeliveryService findDeliveryService;

    @Test
    void shouldCompleteDelivery() {
        var delivery = deliveryValid();

        when(findDeliveryService.find(delivery.getId())).thenReturn(delivery);

        assertDoesNotThrow(() -> deliveryCompletionService.complete(delivery.getId()));

        verify(deliveryRepository, atLeastOnce()).save(any(Delivery.class));
    }

    @Test
    void shouldThrowBusinessException_whenCompleteWasCalled() {
        when(findDeliveryService.find(INVALID_DELIVERY_ID))
                .thenThrow(DataForBusinessException.DELIVERY_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_DELIVERY_ID)));

        assertThrows(BusinessException.class, () -> deliveryCompletionService.complete(INVALID_DELIVERY_ID),
                DataForBusinessException.DELIVERY_NOT_FOUND.getMessage());
    }

    @Test
    void shouldCancelDelivery() {
        var delivery = deliveryValid();

        when(findDeliveryService.find(delivery.getId())).thenReturn(delivery);

        assertDoesNotThrow(() -> deliveryCompletionService.cancel(delivery.getId()));

        verify(deliveryRepository, atLeastOnce()).save(any(Delivery.class));
    }

    @Test
    void shouldThrowBusinessException_whenCancelWasCalled() {
        when(findDeliveryService.find(INVALID_DELIVERY_ID))
                .thenThrow(DataForBusinessException.DELIVERY_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_DELIVERY_ID)));

        assertThrows(BusinessException.class, () -> deliveryCompletionService.cancel(INVALID_DELIVERY_ID),
                DataForBusinessException.DELIVERY_NOT_FOUND.getMessage());
    }


}