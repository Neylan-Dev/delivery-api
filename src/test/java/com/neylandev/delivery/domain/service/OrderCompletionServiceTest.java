//package com.neylandev.delivery.domain.service;
//
//import com.neylandev.delivery.domain.enums.DataForBusinessException;
//import com.neylandev.delivery.domain.enums.OrderStatus;
//import com.neylandev.delivery.domain.model.Order;
//import com.neylandev.delivery.domain.repository.OrderRepository;
//import com.neylandev.delivery.infrastructure.exception.BusinessException;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static com.neylandev.delivery.DataForTests.INVALID_DELIVERY_ID;
//import static com.neylandev.delivery.DataForTests.deliveryValid;
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class OrderCompletionServiceTest {
//
//    @InjectMocks
//    private OrderCompletionService orderCompletionService;
//
//    @Mock
//    private OrderRepository orderRepository;
//
//    @Mock
//    private OrderService orderService;
//
//    @Test
//    void shouldCompleteOrder() {
//        var delivery = deliveryValid();
//
//        when(orderService.find(delivery.getId())).thenReturn(delivery);
//
//        ArgumentCaptor<Order> deliveryArgumentCaptor = ArgumentCaptor.forClass(Order.class);
//        assertDoesNotThrow(() -> orderCompletionService.complete(delivery.getId()));
//
//        verify(orderRepository, atLeastOnce()).save(deliveryArgumentCaptor.capture());
//        var value = deliveryArgumentCaptor.getValue();
//        assertEquals(OrderStatus.FINALIZED, value.getOrderStatus());
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenCompleteWasCalled() {
//        when(orderService.find(INVALID_DELIVERY_ID))
//                .thenThrow(DataForBusinessException.DELIVERY_NOT_FOUND
//                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_DELIVERY_ID)));
//
//        assertThrows(BusinessException.class, () -> orderCompletionService.complete(INVALID_DELIVERY_ID),
//                DataForBusinessException.DELIVERY_NOT_FOUND.getMessage());
//    }
//
//    @Test
//    void shouldCancelOrder() {
//        var delivery = deliveryValid();
//
//        when(orderService.find(delivery.getId())).thenReturn(delivery);
//
//        ArgumentCaptor<Order> deliveryArgumentCaptor = ArgumentCaptor.forClass(Order.class);
//        assertDoesNotThrow(() -> orderCompletionService.cancel(delivery.getId()));
//
//        verify(orderRepository, atLeastOnce()).save(deliveryArgumentCaptor.capture());
//        var value = deliveryArgumentCaptor.getValue();
//        assertEquals(OrderStatus.CANCELLED, value.getOrderStatus());
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenCancelWasCalled() {
//        when(orderService.find(INVALID_DELIVERY_ID))
//                .thenThrow(DataForBusinessException.DELIVERY_NOT_FOUND
//                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_DELIVERY_ID)));
//
//        assertThrows(BusinessException.class, () -> orderCompletionService.cancel(INVALID_DELIVERY_ID),
//                DataForBusinessException.DELIVERY_NOT_FOUND.getMessage());
//    }
//
//
//}