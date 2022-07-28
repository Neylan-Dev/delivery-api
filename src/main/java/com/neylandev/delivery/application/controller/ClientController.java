package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.ClientRequestDto;
import com.neylandev.delivery.application.response.ClientResponseDto;
import com.neylandev.delivery.domain.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "/clients/", tags = "Endpoints responsáveis por gerenciar os dados de Clientes")
public class ClientController {

    private final ClientService clientService;

    @ApiOperation(value = "Retorna todos clientes cadastrados", response = ClientResponseDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornando lista de clientes"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar busca de clientes"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> listAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @ApiOperation(value = "Busca um cliente por id", response = ClientResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 400, message = "Má solicitação para buscar os dados do cliente"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar busca de cliente por id"),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.findById(clientId));
    }

    @ApiOperation(value = "Cadastra um novo cliente", response = ClientResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para cadastrar os dados de cliente"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar cadastro de cliente"),
            @ApiResponse(code = 409, message = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        return new ResponseEntity<>(clientService.create(clientRequestDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza dados de um cliente por id", response = ClientResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para atualizar os dados de cliente"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar atualização de cliente"),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PutMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> update(@PathVariable Long clientId, @RequestBody @Valid ClientRequestDto clientRequestDto) {
        return ResponseEntity.ok().body(clientService.update(clientId, clientRequestDto));
    }

    @ApiOperation(value = "Deleta dados de um cliente por id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cliente excluído com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para excluir os dados de cliente"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar exclusão de cliente"),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId) {
        clientService.delete(clientId);
        return ResponseEntity.noContent().build();
    }
}
