package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.StockRequestDto;
import com.neylandev.delivery.application.response.StockResponseDto;
import com.neylandev.delivery.domain.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products/{productId}/stock")
@RequiredArgsConstructor
@Api(value = "/products/{productId}/stock", tags = "Endpoints responsáveis por gerenciar os dados de estoque")
public class StockController {

    private final StockService stockService;

    @ApiOperation(value = "Adiciona um produto no estoque", response = StockResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto adicionado com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para adiconar produto no estoque"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar adição no estoque"),
            @ApiResponse(code = 409, message = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PostMapping("/in")
    public ResponseEntity<StockResponseDto> addStock(@PathVariable Long productId, @RequestBody @Valid StockRequestDto stockRequestDto) {
        return new ResponseEntity<>(stockService.addStock(productId, stockRequestDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Remove um produto do estoque", response = StockResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto removido com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para remover produto do estoque"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar remoção do estoque"),
            @ApiResponse(code = 409, message = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PostMapping("/out")
    public ResponseEntity<StockResponseDto> removeStock(@PathVariable Long productId, @RequestBody @Valid StockRequestDto stockRequestDto) {
        return new ResponseEntity<>(stockService.removeStock(productId, stockRequestDto), HttpStatus.OK);
    }
}
