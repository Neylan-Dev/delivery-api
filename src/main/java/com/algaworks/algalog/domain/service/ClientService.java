package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.ClientDto;
import com.algaworks.algalog.domain.enums.DataForBusinessException;
import com.algaworks.algalog.domain.repository.ClientRepository;
import com.algaworks.algalog.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.algaworks.algalog.domain.utils.ParseObjects.*;

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
        validate(clientDto);
        return clientToClientDto(clientRepository.save(clientDtoToClient(clientDto)));
    }


    @Transactional
    public ClientDto update(Long clientId, ClientDto clientDto) {
        if (clientRepository.existsById(clientId)) {
            clientDto.setId(clientId);
            validate(clientDto);
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

    private void validate(ClientDto clientDto){
        validateEmail(clientDto);
        validateTelephone(clientDto);
    }

    private void validateTelephone(ClientDto clientDto){
        clientRepository.findByTelephone(clientDto.getTelephone()).ifPresent(client -> {
            if(Objects.isNull(clientDto.getId()) || !clientDto.getId().equals(client.getId())){
                throw DataForBusinessException.TELEPHONE_EXISTS.asBusinessExceptionWithDescriptionFormatted(clientDto.getTelephone());
            }
        });
    }

    private void validateEmail(ClientDto clientDto){
        clientRepository.findByEmail(clientDto.getEmail()).ifPresent(client -> {
            if(Objects.isNull(clientDto.getId()) || !clientDto.getId().equals(client.getId())){
                throw DataForBusinessException.EMAIL_EXISTS.asBusinessExceptionWithDescriptionFormatted(clientDto.getEmail());
            }
        });
    }

}
