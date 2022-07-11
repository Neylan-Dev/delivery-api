package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.ClientDto;
import com.algaworks.algalog.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDto>> listAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.findById(clientId));
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody @Valid ClientDto clientDto) {
        return new ResponseEntity<>(clientService.create(clientDto), HttpStatus.CREATED);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDto> update(@PathVariable Long clientId, @RequestBody @Valid ClientDto clientDto) {
        return ResponseEntity.ok().body(clientService.update(clientId, clientDto));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId) {
        clientService.delete(clientId);
        return ResponseEntity.noContent().build();
    }
}
