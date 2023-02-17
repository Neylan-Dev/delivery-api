package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

//    public Payment save(OrderRequestDto orderRequestDto) {
//        Client client = findClientById(orderRequestDto.getClientId());
//        var order = ParseObjects.orderRequestDtoToOrder(orderRequestDto);
//        order.setOrderStatus(OrderStatus.PENDING);
//        order.setOrderedDate(LocalDateTime.now());
//        order.setClient(client);
//        return ParseObjects.orderToOrderResponseDto(orderRepository.save(order));
//    }
//
//    private Client findClientById(Long clientId) {
//        return ParseObjects.clientResponseDtoToClient(clientService.findById(clientId));
//    }
//
//    public List<OrderResponseDto> findAll() {
//        return ParseObjects.listOrderToListOrderResponseDto(orderRepository.findAll());
//    }
//
//    public OrderResponseDto findById(Long orderId) {
//        return ParseObjects.orderToOrderResponseDto(orderRepository.findById(orderId)
//                .orElseThrow(() -> DataForBusinessException.ORDER_NOT_FOUND
//                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(orderId))));
//    }
}
