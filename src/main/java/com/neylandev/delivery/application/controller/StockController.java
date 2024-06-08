package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.StockRequestDto;
import com.neylandev.delivery.application.response.StockResponseDto;
import com.neylandev.delivery.domain.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products/{productId}/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @Operation(summary = "Adiciona um produto no estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para adiconar produto no estoque"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar adição no estoque"),
            @ApiResponse(responseCode = "409", description = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @PostMapping("/in")
    public ResponseEntity<StockResponseDto> addStock(@PathVariable Long productId, @RequestBody @Valid StockRequestDto stockRequestDto) {
        return new ResponseEntity<>(stockService.addStock(productId, stockRequestDto), HttpStatus.OK);
    }

    @Operation(summary = "Remove um produto do estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para remover produto do estoque"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar remoção do estoque"),
            @ApiResponse(responseCode = "409", description = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @PostMapping("/out")
    public ResponseEntity<StockResponseDto> removeStock(@PathVariable Long productId, @RequestBody @Valid StockRequestDto stockRequestDto) {
        return new ResponseEntity<>(stockService.removeStock(productId, stockRequestDto), HttpStatus.OK);
    }
}
