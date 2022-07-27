package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.infrastructure.exception.BusinessException;
import com.algaworks.algalog.domain.enums.DataForBusinessException;
import com.algaworks.algalog.domain.enums.DeliveryStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static com.algaworks.algalog.DataForTests.VALID_DELIVERY_ID;
import static com.algaworks.algalog.DataForTests.VALID_DESCRIPTION;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DeliveryTest {

    private final Delivery delivery = Delivery.builder()
            .id(VALID_DELIVERY_ID)
            .occurrences(new ArrayList<>())
            .build();

    @Test
    void shouldAddAndGetOccurrence() {

        var occurrence = delivery.addAndGetOccurrence(VALID_DESCRIPTION);

        assertEquals(delivery, occurrence.getDelivery());
        assertEquals(VALID_DESCRIPTION, occurrence.getDescription());
    }

    @Test
    void shouldComplete() {
        delivery.setDeliveryStatus(DeliveryStatus.PENDING);

        assertDoesNotThrow(delivery::complete);

        assertEquals(DeliveryStatus.FINALIZED, delivery.getDeliveryStatus());
    }

    @Test
    void shouldThrowBusinessException_whenCompleteWasCalled() {
        delivery.setDeliveryStatus(DeliveryStatus.CANCELLED);

        assertThrows(BusinessException.class, delivery::complete,
                DataForBusinessException.DELIVERY_CANNOT_BE_COMPLETED.getMessage());
    }

    @Test
    void shouldCancel() {
        delivery.setDeliveryStatus(DeliveryStatus.PENDING);

        assertDoesNotThrow(delivery::cancel);

        assertEquals(DeliveryStatus.CANCELLED, delivery.getDeliveryStatus());
    }

    @Test
    void shouldThrowBusinessException_whenCancelWasCalled() {
        delivery.setDeliveryStatus(DeliveryStatus.FINALIZED);

        assertThrows(BusinessException.class, delivery::cancel,
                DataForBusinessException.DELIVERY_CANNOT_BE_CANCELED.getMessage());
    }

}