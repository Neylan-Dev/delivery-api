package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.application.request.OrderRequestDto;
import com.neylandev.delivery.application.response.OrderResponseDto;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.enums.OrderStatus;
import com.neylandev.delivery.domain.model.Client;
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

    @Transactional
    public OrderResponseDto save(OrderRequestDto orderRequestDto) {
        Client client = findClientById(orderRequestDto.getClientId());
        var order = ParseObjects.orderRequestDtoToOrder(orderRequestDto);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderedDate(LocalDateTime.now());
        order.setClient(client);
        return ParseObjects.orderToOrderResponseDto(orderRepository.save(order));
    }

    private Client findClientById(Long clientId) {
        return ParseObjects.clientResponseDtoToClient(clientService.findById(clientId));
    }

    public List<OrderResponseDto> findAll() {
        return ParseObjects.listOrderToListOrderResponseDto(orderRepository.findAll());
    }

    public OrderResponseDto findById(Long orderId) {
        return ParseObjects.orderToOrderResponseDto(orderRepository.findById(orderId)
                .orElseThrow(() -> DataForBusinessException.ORDER_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(orderId))));
    }
}
