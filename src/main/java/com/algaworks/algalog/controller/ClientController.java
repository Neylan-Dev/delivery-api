package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.ClientDto;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    @GetMapping
    public List<ClientDto> listAll() {
        return modelMapper.map(clientRepository.findAll(), new TypeToken<List<ClientDto>>() {
        }.getType());
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long clientId) {
        return clientRepository.findById(clientId).map(client -> ResponseEntity.ok(modelMapper.map(client, ClientDto.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto create(@RequestBody @Valid ClientDto clientDto) {
        return modelMapper.map(clientRepository.save(modelMapper.map(clientDto, Client.class)), ClientDto.class);
    }

    @PutMapping
    public ResponseEntity<ClientDto> update(@PathVariable Long clientId, @RequestBody @Valid ClientDto clientDto) {
        if (clientRepository.existsById(clientId)) {
            clientDto.setId(clientId);
            return ResponseEntity.ok().body(modelMapper.map(clientRepository.save(modelMapper.map(clientDto, Client.class)), ClientDto.class));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long clientId) {
        if (clientRepository.existsById(clientId)) {
            clientRepository.deleteById(clientId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
