package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client){
        return clientRepository.save(client);
    }
}
