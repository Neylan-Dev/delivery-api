package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.model.Client;
import com.neylandev.delivery.domain.repository.ClientRepository;
import com.neylandev.delivery.infrastructure.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static com.neylandev.delivery.DataForTests.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;


    @Test
    void shouldFindAllClients() {
        var client = clientValid();
        when(clientRepository.findAll()).thenReturn(Collections.singletonList(client));

        var clientResponseDtoList = clientService.findAll();

        assertEquals(client.getId(), clientResponseDtoList.stream().iterator().next().getId());
        assertEquals(client.getEmail(), clientResponseDtoList.stream().iterator().next().getEmail());
        assertEquals(client.getName(), clientResponseDtoList.stream().iterator().next().getName());
        assertEquals(client.getTelephone(), clientResponseDtoList.stream().iterator().next().getTelephone());
    }

    @Test
    void shouldFindClientById() {
        var client = clientValid();
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        var clientResponseDtoList = clientService.findById(client.getId());

        assertEquals(client.getId(), clientResponseDtoList.getId());
        assertEquals(client.getEmail(), clientResponseDtoList.getEmail());
        assertEquals(client.getName(), clientResponseDtoList.getName());
        assertEquals(client.getTelephone(), clientResponseDtoList.getTelephone());
    }

    @Test
    void shouldThrowBusinessException_whenFindClientById() {
        when(clientRepository.findById(INVALID_CLIENT_ID)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> clientService.findById(INVALID_CLIENT_ID),
                DataForBusinessException.CLIENT_NOT_FOUND.getMessage());
    }

    @Test
    void shouldSaveClient() {
        var client = clientValid();

        var clientRequestDto = clientRequestDtoValid();

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        var clientResponseDtoList = clientService.create(clientRequestDto);

        assertEquals(clientRequestDto.getEmail(), clientResponseDtoList.getEmail());
        assertEquals(clientRequestDto.getName(), clientResponseDtoList.getName());
        assertEquals(clientRequestDto.getTelephone(), clientResponseDtoList.getTelephone());
    }

    @Test
    void shouldUpdateClient() {
        var client = clientValid();

        var clientRequestDto = clientRequestDtoValid();

        when(clientRepository.existsById(VALID_CLIENT_ID)).thenReturn(true);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ArgumentCaptor<Client> clientArgumentCaptor = ArgumentCaptor.forClass(Client.class);

        var clientResponseDto = clientService.update(VALID_CLIENT_ID, clientRequestDto);

        verify(clientRepository, atLeastOnce()).save(clientArgumentCaptor.capture());
        var clientArgumentCaptorValue = clientArgumentCaptor.getValue();
        assertEquals(VALID_CLIENT_ID, clientArgumentCaptorValue.getId());
        assertEquals(clientRequestDto.getEmail(), clientResponseDto.getEmail());
        assertEquals(clientRequestDto.getName(), clientResponseDto.getName());
        assertEquals(clientRequestDto.getTelephone(), clientResponseDto.getTelephone());
    }

    @Test
    void shouldThrowBusinessException_whenUpdateClient() {
        var clientRequestDto = clientRequestDtoValid();

        when(clientRepository.existsById(INVALID_CLIENT_ID))
                .thenThrow(DataForBusinessException.CLIENT_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_CLIENT_ID)));

        assertThrows(BusinessException.class, () -> clientService.update(INVALID_CLIENT_ID, clientRequestDto),
                DataForBusinessException.CLIENT_NOT_FOUND.getMessage());
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    void shouldDeleteClient() {
        var client = clientValid();

        when(clientRepository.existsById(client.getId())).thenReturn(true);

        assertDoesNotThrow(() -> clientService.delete(client.getId()));
        verify(clientRepository, atLeastOnce()).deleteById(anyLong());
    }

    @Test
    void shouldThrowBusinessException_whenDeleteClient() {
        var clientRequestDto = clientRequestDtoValid();

        when(clientRepository.existsById(INVALID_CLIENT_ID))
                .thenThrow(DataForBusinessException.CLIENT_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_CLIENT_ID)));

        assertThrows(BusinessException.class, () -> clientService.update(INVALID_CLIENT_ID, clientRequestDto),
                DataForBusinessException.CLIENT_NOT_FOUND.getMessage());
        verify(clientRepository, never()).deleteById(anyLong());
    }

}