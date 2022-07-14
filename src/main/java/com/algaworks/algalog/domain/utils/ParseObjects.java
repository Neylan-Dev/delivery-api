package com.algaworks.algalog.domain.utils;

import com.algaworks.algalog.domain.dto.ClientRequestDto;
import com.algaworks.algalog.domain.dto.ClientResponseDto;
import com.algaworks.algalog.domain.dto.DeliveryRequestDto;
import com.algaworks.algalog.domain.dto.DeliveryResponseDto;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.model.Delivery;
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

    public static DeliveryResponseDto deliveryToDeliveryResponseDto(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryResponseDto.class);
    }

    public static Delivery deliveryRequestDtoToDelivery(DeliveryRequestDto deliveryRequestDto) {
        return modelMapper.map(deliveryRequestDto, Delivery.class);
    }

    public static List<DeliveryResponseDto> listDeliveryToListDeliveryResponseDto(List<Delivery> deliveries) {
        return deliveries.stream().map(ParseObjects::deliveryToDeliveryResponseDto).collect(Collectors.toList());
    }
}
