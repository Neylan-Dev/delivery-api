package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.application.request.ClientRequestDto;
import com.algaworks.algalog.application.response.ClientResponseDto;
import com.algaworks.algalog.domain.enums.DataForBusinessException;
import com.algaworks.algalog.domain.repository.ClientRepository;
import com.algaworks.algalog.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.algaworks.algalog.domain.utils.ParseObjects.clientRequestDtoToClient;
import static com.algaworks.algalog.domain.utils.ParseObjects.clientToClientResponseDto;
import static com.algaworks.algalog.domain.utils.ParseObjects.listClientToListClientResponseDto;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientResponseDto> findAll() {
        return listClientToListClientResponseDto(clientRepository.findAll());
    }

    public ClientResponseDto findById(Long clientId) {
        return clientRepository.findById(clientId).map(ParseObjects::clientToClientResponseDto)
                .orElseThrow(() -> DataForBusinessException.CLIENT_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(clientId)));
    }

    @Transactional
    public ClientResponseDto create(ClientRequestDto clientRequestDto) {
        return clientToClientResponseDto(clientRepository.save(clientRequestDtoToClient(clientRequestDto)));
    }


    @Transactional
    public ClientResponseDto update(Long clientId, ClientRequestDto clientRequestDto) {
        if (clientRepository.existsById(clientId)) {
            var client = clientRequestDtoToClient(clientRequestDto);
            client.setId(clientId);
            return clientToClientResponseDto(clientRepository.save(client));
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
