package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.OccurrenceRequestDto;
import com.neylandev.delivery.application.response.OccurrenceResponseDto;
import com.neylandev.delivery.domain.service.OccurrenceService;
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
@RequestMapping("/deliveries/{deliveryId}/occurrences")
@RequiredArgsConstructor
public class OccurrenceController {

    private final OccurrenceService occurrenceService;

    @Operation(summary = "Cadastra uma nova ocorrência")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ocorrência cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para cadastrar os dados de ocorrência"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar cadastro de ocorrência"),
            @ApiResponse(responseCode = "409", description = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @PostMapping
    public ResponseEntity<OccurrenceResponseDto> registerOccurrence(@PathVariable Long orderId, @RequestBody @Valid OccurrenceRequestDto occurrenceRequestDto) {
        return new ResponseEntity<>(occurrenceService.registerOccurrence(orderId, occurrenceRequestDto.getDescription()), HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna todas ocorrência de uma entrega por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista de ocorrências de uma entrega por id"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar busca de ocorrências"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @GetMapping
    public ResponseEntity<List<OccurrenceResponseDto>> listAll(@PathVariable Long deliveryId) {
        return ResponseEntity.ok(occurrenceService.findAllOccurrencesOfOrder(deliveryId));
    }

}
