package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.DeliveryRequestDto;
import com.neylandev.delivery.application.response.DeliveryResponseDto;
import com.neylandev.delivery.domain.service.DeliveryCompletionService;
import com.neylandev.delivery.domain.service.DeliveryCreationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/deliveries")
@RequiredArgsConstructor
@Api(value = "/deliveries/", tags = "Endpoints responsáveis por gerenciar os dados de entregas")
public class DeliveryController {

    private final DeliveryCreationService deliveryCreationService;
    private final DeliveryCompletionService deliveryCompletionService;

    @ApiOperation(value = "Cadastra uma nova entrega", response = DeliveryResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entrega cadastrada com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para cadastrar os dados de entrega"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar cadastro de entrega"),
            @ApiResponse(code = 409, message = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PostMapping
    public ResponseEntity<DeliveryResponseDto> create(@RequestBody @Valid DeliveryRequestDto deliveryRequestDto) {
        return new ResponseEntity<>(deliveryCreationService.save(deliveryRequestDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retorna todas entregas cadastradas", response = DeliveryResponseDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornando lista de entregas"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar busca de entregas"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @GetMapping
    public ResponseEntity<List<DeliveryResponseDto>> listAll() {
        return ResponseEntity.ok(deliveryCreationService.findAll());
    }

    @ApiOperation(value = "Busca uma entrega por id", response = DeliveryResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entrega encontrada"),
            @ApiResponse(code = 400, message = "Má solicitação para buscar os dados de entrega"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar a busca de entrega por id"),
            @ApiResponse(code = 404, message = "Entrega não encontrada"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryResponseDto> findById(@PathVariable Long deliveryId) {
        return ResponseEntity.ok(deliveryCreationService.findById(deliveryId));
    }

    @ApiOperation(value = "Finaliza uma entrega por id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Entrega finalizada com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para finalizar entrega"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar finalização de entrega"),
            @ApiResponse(code = 404, message = "Entrega não encontrada"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PutMapping("/{deliveryId}/complete")
    public ResponseEntity<Void> complete(@PathVariable Long deliveryId) {
        deliveryCompletionService.complete(deliveryId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Cancela uma entrega por id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Entrega cancelada com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para cancelar entrega"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar cancelamento de entrega"),
            @ApiResponse(code = 404, message = "Entrega não encontrada"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PutMapping("/{deliveryId}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long deliveryId) {
        deliveryCompletionService.cancel(deliveryId);
        return ResponseEntity.noContent().build();
    }

}
