package com.algaworks.algalog.domain.utils;

import com.algaworks.algalog.application.request.ClientRequestDto;
import com.algaworks.algalog.application.response.ClientResponseDto;
import com.algaworks.algalog.application.request.DeliveryRequestDto;
import com.algaworks.algalog.application.response.DeliveryResponseDto;
import com.algaworks.algalog.application.response.OccurrenceResponseDto;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.model.Occurrence;
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

    public static OccurrenceResponseDto occurrenceToOccurrenceResponseDto(Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceResponseDto.class);
    }

    public static List<OccurrenceResponseDto> listOccurrenceToListOccurrenceResponseDto(List<Occurrence> occurrences) {
        return occurrences.stream().map(ParseObjects::occurrenceToOccurrenceResponseDto).collect(Collectors.toList());
    }
}
