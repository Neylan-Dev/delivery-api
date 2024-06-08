package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.ClientCreateRequestDto;
import com.neylandev.delivery.application.request.ClientUpdateRequestDto;
import com.neylandev.delivery.application.response.ClientResponseDto;
import com.neylandev.delivery.domain.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Retorna todos clientes cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista de clientes"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar busca de clientes"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> listAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @Operation(summary = "Busca um cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para buscar os dados do cliente"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar busca de cliente por id"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.findById(clientId));
    }

    @Operation(summary = "Cadastra um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para cadastrar os dados de cliente"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar cadastro de cliente"),
            @ApiResponse(responseCode = "409", description = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@RequestBody @Valid ClientCreateRequestDto clientCreateRequestDto) {
        return new ResponseEntity<>(clientService.create(clientCreateRequestDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza dados de um cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para atualizar os dados de cliente"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar atualização de cliente"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @PutMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> update(@PathVariable Long clientId, @RequestBody @Valid ClientUpdateRequestDto clientUpdateRequestDto) {
        return ResponseEntity.ok().body(clientService.update(clientId, clientUpdateRequestDto));
    }

    @Operation(summary = "Deleta dados de um cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para excluir os dados de cliente"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar exclusão de cliente"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId) {
        clientService.delete(clientId);
        return ResponseEntity.noContent().build();
    }
}
