package com.neylandev.delivery.domain.utils;

import com.neylandev.delivery.application.request.ClientRequestDto;
import com.neylandev.delivery.application.request.OrderRequestDto;
import com.neylandev.delivery.application.response.ClientResponseDto;
import com.neylandev.delivery.application.response.OccurrenceResponseDto;
import com.neylandev.delivery.application.response.OrderResponseDto;
import com.neylandev.delivery.domain.model.Client;
import com.neylandev.delivery.domain.model.Delivery;
import com.neylandev.delivery.domain.model.Occurrence;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParseObjects {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ClientResponseDto clientToClientResponseDto(Client client) {
        return modelMapper.map(client, ClientResponseDto.class);
    }

    public static Client clientRequestDtoToClient(ClientRequestDto clientRequestDto) {
        return modelMapper.map(clientRequestDto, Client.class);
    }

    public static List<ClientResponseDto> listClientToListClientResponseDto(List<Client> clients) {
        return clients.stream().map(ParseObjects::clientToClientResponseDto).collect(Collectors.toList());
    }

    public static OrderResponseDto deliveryToDeliveryResponseDto(Delivery delivery) {
        return modelMapper.map(delivery, OrderResponseDto.class);
    }

    public static Delivery deliveryRequestDtoToDelivery(OrderRequestDto orderRequestDto) {
        return modelMapper.map(orderRequestDto, Delivery.class);
    }

    public static List<OrderResponseDto> listDeliveryToListDeliveryResponseDto(List<Delivery> deliveries) {
        return deliveries.stream().map(ParseObjects::deliveryToDeliveryResponseDto).collect(Collectors.toList());
    }

    public static OccurrenceResponseDto occurrenceToOccurrenceResponseDto(Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceResponseDto.class);
    }

    public static List<OccurrenceResponseDto> listOccurrenceToListOccurrenceResponseDto(List<Occurrence> occurrences) {
        return occurrences.stream().map(ParseObjects::occurrenceToOccurrenceResponseDto).collect(Collectors.toList());
    }
}
