package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.OrderRequestDto;
import com.neylandev.delivery.application.request.PaymentRequestDto;
import com.neylandev.delivery.application.response.OrderResponseDto;
import com.neylandev.delivery.domain.service.OrderService;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Retorna todos os pedidos cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista de pedidos"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar busca de pedidos"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> listAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @Operation(summary ="Cadastra um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para cadastrar os dados de pedido"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar cadastro de pedido"),
            @ApiResponse(responseCode = "409", description = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        return new ResponseEntity<>(orderService.save(orderRequestDto), HttpStatus.CREATED);
    }

    @Operation(summary ="Busca um pedido por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para buscar os dados de pedido"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar a busca de pedido por id"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.findOrderResponseDtoById(orderId));
    }

    @Operation(summary ="Paga um pedido por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido pago com sucesso"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para pagar pedido"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar pagamento de pedido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @PostMapping("/{orderId}/pay")
    public ResponseEntity<Void> pay(@PathVariable Long orderId, List<PaymentRequestDto> paymentRequestDtos) {
        orderService.pay(orderId, paymentRequestDtos);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary ="Cancela um pedido por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido cancelado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para cancelar pedido"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar cancelamento de pedido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long orderId) {
        orderService.cancel(orderId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary ="Sai com um pedido para entrega")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido despachado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para despachar pedido"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar despachar pedido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @PutMapping("/{orderId}/dispatch")
    public ResponseEntity<Void> dispatch(@PathVariable Long orderId) {
        orderService.dispatch(orderId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary ="Pedido entregue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido entregue com sucesso"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para entregar pedido"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar entrega de pedido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @PutMapping("/{orderId}/delivery")
    public ResponseEntity<Void> delivery(@PathVariable Long orderId) {
        orderService.delivery(orderId);
        return ResponseEntity.noContent().build();
    }

}
