package com.algaworks.algalog.domain.utils;

import com.algaworks.algalog.domain.ClientDto;
import com.algaworks.algalog.domain.DeliveryDto;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.model.Delivery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParseObjects {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ClientDto clientToClientDto(Client client) {
        return modelMapper.map(client, ClientDto.class);
    }

    public static Client clientDtoToClient(ClientDto clientDto) {
        return modelMapper.map(clientDto, Client.class);
    }

    public static List<ClientDto> listClientToListClientDto(List<Client> clients) {
        var listClientDto = new ArrayList<ClientDto>();
        clients.forEach(client -> listClientDto.add(clientToClientDto(client)));
        return listClientDto;
    }

    public static DeliveryDto deliveryToDeliveryDto(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryDto.class);
    }

    public static Delivery deliveryDtoToDelivery(DeliveryDto deliveryDto) {
        return modelMapper.map(deliveryDto, Delivery.class);
    }

    public static List<DeliveryDto> listDeliveryToListDeliveryDto(List<Delivery> deliveries) {
        var listDeliveryDto = new ArrayList<DeliveryDto>();
        deliveries.forEach(delivery -> listDeliveryDto.add(deliveryToDeliveryDto(delivery)));
        return listDeliveryDto;
    }
}
