package com.algaworks.algalog.service;

import com.algaworks.algalog.domain.ClientDto;
import com.algaworks.algalog.domain.enums.DataForBusinessException;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    public List<ClientDto> findAll() {
        return modelMapper.map(clientRepository.findAll(), new TypeToken<List<ClientDto>>() {
        }.getType());
    }

    public ClientDto findById(Long clientId) {
        return clientRepository.findById(clientId).map(client -> modelMapper.map(client, ClientDto.class))
                .orElseThrow(() -> DataForBusinessException.CLIENT_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(clientId)));
    }

    public ClientDto create(ClientDto clientDto) {
        return modelMapper.map(clientRepository.save(modelMapper.map(clientDto, Client.class)), ClientDto.class);
    }

    public ClientDto update(Long clientId, ClientDto clientDto) {
        if (clientRepository.existsById(clientId)) {
            clientDto.setId(clientId);
            return modelMapper.map(clientRepository.save(modelMapper.map(clientDto, Client.class)), ClientDto.class);
        }
        throw DataForBusinessException.CLIENT_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(clientId));
    }

    public void delete(Long clientId) {
        if (clientRepository.existsById(clientId)) {
            clientRepository.deleteById(clientId);
        } else {
            throw DataForBusinessException.CLIENT_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(clientId));
        }
    }

}
