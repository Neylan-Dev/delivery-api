package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.application.request.ClientRequestDto;
import com.neylandev.delivery.application.response.ClientResponseDto;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.repository.ClientRepository;
import com.neylandev.delivery.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientResponseDto> findAll() {
        return ParseObjects.listClientToListClientResponseDto(clientRepository.findAll());
    }

    public ClientResponseDto findById(Long clientId) {
        return clientRepository.findById(clientId).map(ParseObjects::clientToClientResponseDto)
                .orElseThrow(() -> DataForBusinessException.CLIENT_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(clientId)));
    }

    @Transactional
    public ClientResponseDto create(ClientRequestDto clientRequestDto) {
        return ParseObjects.clientToClientResponseDto(clientRepository.save(ParseObjects.clientRequestDtoToClient(clientRequestDto)));
    }


    @Transactional
    public ClientResponseDto update(Long clientId, ClientRequestDto clientRequestDto) {
        if (clientRepository.existsById(clientId)) {
            var client = ParseObjects.clientRequestDtoToClient(clientRequestDto);
            client.setId(clientId);
            return ParseObjects.clientToClientResponseDto(clientRepository.save(client));
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
