package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.application.request.ClientCreateRequestDto;
import com.neylandev.delivery.application.request.ClientUpdateRequestDto;
import com.neylandev.delivery.application.response.ClientResponseDto;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.model.Client;
import com.neylandev.delivery.domain.repository.ClientRepository;
import com.neylandev.delivery.domain.utils.CopyUtils;
import com.neylandev.delivery.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public ClientResponseDto create(ClientCreateRequestDto clientCreateRequestDto) {
        return ParseObjects.clientToClientResponseDto(clientRepository.save(ParseObjects.clientCreateRequestDtoToClient(clientCreateRequestDto)));
    }


    @Transactional
    public ClientResponseDto update(Long clientId, ClientUpdateRequestDto clientUpdateRequestDto) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            Client client = (Client) CopyUtils.updateMyObject(optionalClient.get(),
                    ParseObjects.clientUpdateRequestDtoToClient(clientUpdateRequestDto));

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
