package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.application.request.OrderRequestDto;
import com.neylandev.delivery.application.request.PaymentRequestDto;
import com.neylandev.delivery.application.request.StockRequestDto;
import com.neylandev.delivery.application.response.OrderResponseDto;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.enums.OrderStatus;
import com.neylandev.delivery.domain.model.Client;
import com.neylandev.delivery.domain.model.Order;
import com.neylandev.delivery.domain.repository.OrderRepository;
import com.neylandev.delivery.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final PaymentService paymentService;
    private final StockService stockService;

    @Transactional
    public OrderResponseDto save(OrderRequestDto orderRequestDto) {
        Client client = findClientById(orderRequestDto.getClientId());
        var order = ParseObjects.orderRequestDtoToOrder(orderRequestDto);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderedDate(LocalDateTime.now());
        order.setClient(client);
        order = orderRepository.save(order);
        paymentService.save(order, orderRequestDto.getPaymentRequestDtos());
        order.getOrderItems().forEach(orderItem -> {
            stockService.removeStock(orderItem.getProduct().getId(), StockRequestDto.builder()
                    .quantity(orderItem.getQuantity())
                    .build());
        });
        changeStatus(order);
        return ParseObjects.orderToOrderResponseDto(order);
    }


    private Client findClientById(Long clientId) {
        return ParseObjects.clientResponseDtoToClient(clientService.findById(clientId));
    }

    public List<OrderResponseDto> findAll() {
        return ParseObjects.listOrderToListOrderResponseDto(orderRepository.findAll());
    }

    public OrderResponseDto findOrderResponseDtoById(Long orderId) {
        return ParseObjects.orderToOrderResponseDto(findById(orderId));
    }

    public Order findById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> DataForBusinessException.ORDER_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(orderId)));
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = findById(orderId);
        if (OrderStatus.canCancel(order.getOrderStatus())) {
            if (order.getOrderStatus().equals(OrderStatus.SHIPPED)) {
                paymentService.refund(order.getPayments());
            } else {
                paymentService.cancel(order.getPayments());
            }
            order.setOrderStatus(OrderStatus.CANCELLED);
            order = orderRepository.save(order);
            order.getOrderItems().forEach(orderItem -> {
                stockService.addStock(orderItem.getProduct().getId(), StockRequestDto.builder()
                        .quantity(orderItem.getQuantity())
                        .build());
            });

        } else {
            throw DataForBusinessException.ORDER_CANNOT_BE_CANCELED.asBusinessExceptionWithDescriptionFormatted(Long.toString(orderId));
        }
    }

    public void pay(Long orderId, List<PaymentRequestDto> paymentRequestDtos) {
        Order order = findById(orderId);
        if (OrderStatus.PROCESSING.equals(order.getOrderStatus())) {
            paymentService.pay(order, paymentRequestDtos);
            changeStatus(order);
        } else {
            throw DataForBusinessException.ORDER_CANNOT_BE_PAID.asBusinessExceptionWithDescriptionFormatted(Long.toString(orderId));
        }
    }

    private void changeStatus(Order order) {
        if (OrderStatus.CANCELLED.equals(order.getOrderStatus()) || OrderStatus.DELIVERED.equals(order.getOrderStatus())) {
            throw DataForBusinessException.ORDER_CANNOT_CHANGE_STATUS.asBusinessExceptionWithDescriptionFormatted(Long.toString(order.getId()));
        } else if (OrderStatus.PENDING.equals(order.getOrderStatus())) {
            order.setOrderStatus(OrderStatus.PROCESSING);
        } else if (OrderStatus.PROCESSING.equals(order.getOrderStatus())) {
            order.setOrderStatus(OrderStatus.SHIPPED);
        } else if (OrderStatus.SHIPPED.equals(order.getOrderStatus()) && order.getDelivered()) {
            order.setOrderStatus(OrderStatus.DELIVERED);
        }
        orderRepository.save(order);
    }

    public void dispatch(Long orderId) {
        Order order = findById(orderId);
        if (!order.getOrderStatus().equals(OrderStatus.PROCESSING)) {
            throw DataForBusinessException.ORDER_CANNOT_BE_DISPATCHED.asBusinessExceptionWithDescriptionFormatted(Long.toString(orderId));
        }
        changeStatus(order);
    }

    public void delivery(Long orderId) {
        Order order = findById(orderId);
        if (!order.getOrderStatus().equals(OrderStatus.SHIPPED)) {
            throw DataForBusinessException.ORDER_CANNOT_BE_DELIVERED.asBusinessExceptionWithDescriptionFormatted(Long.toString(orderId));
        }
        changeStatus(order);
    }
}
