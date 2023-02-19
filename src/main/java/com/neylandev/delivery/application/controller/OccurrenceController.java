package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.OccurrenceRequestDto;
import com.neylandev.delivery.application.response.OccurrenceResponseDto;
import com.neylandev.delivery.domain.service.OccurrenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deliveries/{deliveryId}/occurrences")
@RequiredArgsConstructor
@Api(value = "/deliveries/{deliveryId}/occurrences", tags = "Endpoints responsáveis por gerenciar os dados de ocorrências de entregas")
public class OccurrenceController {

    private final OccurrenceService occurrenceService;

    @ApiOperation(value = "Cadastra uma nova ocorrência", response = OccurrenceResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ocorrência cadastrada com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para cadastrar os dados de ocorrência"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar cadastro de ocorrência"),
            @ApiResponse(code = 409, message = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PostMapping
    public ResponseEntity<OccurrenceResponseDto> registerOccurrence(@PathVariable Long orderId, @RequestBody @Valid OccurrenceRequestDto occurrenceRequestDto) {
        return new ResponseEntity<>(occurrenceService.registerOccurrence(orderId, occurrenceRequestDto.getDescription()), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retorna todas ocorrência de uma entrega por id", response = OccurrenceResponseDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornando lista de ocorrências de uma entrega por id"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar busca de ocorrências"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @GetMapping
    public ResponseEntity<List<OccurrenceResponseDto>> listAll(@PathVariable Long deliveryId) {
        return ResponseEntity.ok(occurrenceService.findAllOccurrencesOfOrder(deliveryId));
    }

}
