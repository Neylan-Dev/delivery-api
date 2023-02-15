package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.model.Occurrence;
import com.neylandev.delivery.domain.repository.OccurrenceRepository;
import com.neylandev.delivery.infrastructure.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.neylandev.delivery.DataForTests.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OccurrenceServiceTest {

    @InjectMocks
    private OccurrenceService occurrenceService;

    @Mock
    private FindDeliveryService findDeliveryService;

    @Mock
    private OccurrenceRepository occurrenceRepository;

    @Test
    void shouldRegisterOccurrence() {
        var delivery = deliveryValid();

        when(findDeliveryService.find(VALID_DELIVERY_ID)).thenReturn(delivery);
        when(occurrenceRepository.save(any(Occurrence.class))).thenReturn(delivery.getOccurrences().iterator().next());

        var occurrenceResponseDto = occurrenceService.registerOccurrence(VALID_DELIVERY_ID, VALID_DESCRIPTION);

        assertEquals(delivery.getId(), occurrenceResponseDto.getOrderId());
    }

    @Test
    void shouldThrowBusinessException_whenDeliveryNotFoundAndRegisterOccurrenceWasCalled() {

        when(findDeliveryService.find(INVALID_DELIVERY_ID))
                .thenThrow(DataForBusinessException.DELIVERY_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_DELIVERY_ID)));

        assertThrows(BusinessException.class,
                () -> occurrenceService.registerOccurrence(INVALID_DELIVERY_ID, VALID_DESCRIPTION),
                DataForBusinessException.DELIVERY_NOT_FOUND.getMessage());
    }

    @Test
    void shouldReturnAllOccurrencesOfDelivery() {
        var delivery = deliveryValid();
        delivery.getOccurrences().iterator().next().setId(VALID_OCCURRENCE_ID);

        when(findDeliveryService.find(VALID_DELIVERY_ID)).thenReturn(delivery);

        var occurrenceResponseDtoList = occurrenceService.findAllOccurrencesOfDelivery(VALID_DELIVERY_ID);

        assertEquals(delivery.getOccurrences().iterator().next().getId(), occurrenceResponseDtoList.iterator().next().getOrderId());
        assertEquals(delivery.getOccurrences().iterator().next().getDescription(), occurrenceResponseDtoList.iterator().next().getDescription());
    }

    @Test
    void shouldThrowBusinessException_whenDeliveryNotFoundAndFindAllOccurrencesOfDeliveryWasCalled() {

        when(findDeliveryService.find(INVALID_DELIVERY_ID))
                .thenThrow(DataForBusinessException.DELIVERY_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_DELIVERY_ID)));

        assertThrows(BusinessException.class,
                () -> occurrenceService.findAllOccurrencesOfDelivery(INVALID_DELIVERY_ID),
                DataForBusinessException.DELIVERY_NOT_FOUND.getMessage());
    }
}