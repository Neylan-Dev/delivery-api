package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.response.OrderResponseDto;
import com.neylandev.delivery.application.response.PaymentResponseDto;
import com.neylandev.delivery.domain.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "/payments/", tags = "Endpoints responsáveis por gerenciar os dados de pagamentos")
public class PaymentController {

    private final PaymentService paymentService;

    @ApiOperation(value = "Retorna todos os pagamentos cadastrados", response = PaymentResponseDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornando lista de pagamentos"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar busca de pagamentos"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> listAll() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @ApiOperation(value = "Busca um pedido por id", response = OrderResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido encontrado"),
            @ApiResponse(code = 400, message = "Má solicitação para buscar os dados de pedido"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar a busca de pedido por id"),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDto> findById(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.findById(paymentId));
    }
}
