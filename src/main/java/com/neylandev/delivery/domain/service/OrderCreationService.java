package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.application.request.OrderRequestDto;
import com.neylandev.delivery.application.response.OrderResponseDto;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.enums.DeliveryStatus;
import com.neylandev.delivery.domain.model.Client;
import com.neylandev.delivery.domain.repository.ClientRepository;
import com.neylandev.delivery.domain.repository.OrderRepository;
import com.neylandev.delivery.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCreationService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final FindDeliveryService findDeliveryService;

    @Transactional
    public OrderResponseDto save(OrderRequestDto orderRequestDto) {
        Client client = findClientById(orderRequestDto.getClientId());
        var delivery = ParseObjects.orderRequestDtoToOrder(orderRequestDto);
        delivery.setDeliveryStatus(DeliveryStatus.PENDING);
        delivery.setOrderedDate(OffsetDateTime.now());
        delivery.setClient(client);
        return ParseObjects.orderToOrderResponseDto(orderRepository.save(delivery));
    }

    private Client findClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> DataForBusinessException.CLIENT_DELIVERY_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(clientId)));
    }

    public List<OrderResponseDto> findAll() {
        return ParseObjects.listOrderToListOrderResponseDto(orderRepository.findAll());
    }

    public OrderResponseDto findById(Long deliveryId) {
        return ParseObjects.orderToOrderResponseDto(findDeliveryService.find(deliveryId));
    }
}
