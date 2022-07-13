package com.algaworks.algalog.domain.utils;

import com.algaworks.algalog.domain.ClientDto;
import com.algaworks.algalog.domain.model.Client;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class ParseObjects {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ClientDto clientToClientDto(Client client) {
        return modelMapper.map(client, ClientDto.class);
    }

    public static Client clientDtoToClient(ClientDto clientDto) {
        return modelMapper.map(clientDto, Client.class);
    }

    public static List<ClientDto> listClientToListClientDto(List<Client> clients) {
        return modelMapper.map(clients, new TypeToken<List<ClientDto>>() {
        }.getType());
    }

}
