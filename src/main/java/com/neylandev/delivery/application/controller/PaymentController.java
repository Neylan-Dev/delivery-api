package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.response.PaymentResponseDto;
import com.neylandev.delivery.domain.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Retorna todos os pagamentos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista de pagamentos"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar busca de pagamentos"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> listAll() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @Operation(summary = "Busca um pedido por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para buscar os dados de pedido"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar a busca de pedido por id"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDto> findById(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.findById(paymentId));
    }
}
