package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.ClientDto;
import com.algaworks.algalog.domain.enums.DataForBusinessException;
import com.algaworks.algalog.domain.repository.ClientRepository;
import com.algaworks.algalog.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.algaworks.algalog.domain.utils.ParseObjects.clientDtoToClient;
import static com.algaworks.algalog.domain.utils.ParseObjects.clientToClientDto;
import static com.algaworks.algalog.domain.utils.ParseObjects.listClientToListClientDto;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientDto> findAll() {
        return listClientToListClientDto(clientRepository.findAll());
    }

    public ClientDto findById(Long clientId) {
        return clientRepository.findById(clientId).map(ParseObjects::clientToClientDto)
                .orElseThrow(() -> DataForBusinessException.CLIENT_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(clientId)));
    }

    @Transactional
    public ClientDto create(ClientDto clientDto) {
        return clientToClientDto(clientRepository.save(clientDtoToClient(clientDto)));
    }


    @Transactional
    public ClientDto update(Long clientId, ClientDto clientDto) {
        if (clientRepository.existsById(clientId)) {
            clientDto.setId(clientId);
            return clientToClientDto(clientRepository.save(clientDtoToClient(clientDto)));
        }
        throw DataForBusinessException.CLIENT_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(clientId));
    }

    @Transactional
    public void delete(Long clientId) {
        if (clientRepository.existsById(clientId)) {
            clientRepository.deleteById(clientId);
        } else {
            throw DataForBusinessException.CLIENT_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(clientId));
        }
    }

}
