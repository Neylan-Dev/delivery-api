package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.OrderRequestDto;
import com.neylandev.delivery.application.request.PaymentRequestDto;
import com.neylandev.delivery.application.response.OrderResponseDto;
import com.neylandev.delivery.domain.service.OrderService;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
@Api(value = "/orders/", tags = "Endpoints responsáveis por gerenciar os dados de pedidos")
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "Cadastra um novo pedido", response = OrderResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pedido cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para cadastrar os dados de pedido"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar cadastro de pedido"),
            @ApiResponse(code = 409, message = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        return new ResponseEntity<>(orderService.save(orderRequestDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retorna todos os pedidos cadastradas", response = OrderResponseDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornando lista de pedidos"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar busca de pedidos"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> listAll() {
        return ResponseEntity.ok(orderService.findAll());
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
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.findOrderResponseDtoById(orderId));
    }

    @ApiOperation(value = "Paga um pedido por id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Pedido pago com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para pagar pedido"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar pagamento de pedido"),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PostMapping("/{orderId}/pay")
    public ResponseEntity<Void> pay(@PathVariable Long orderId, List<PaymentRequestDto> paymentRequestDtos) {
        orderService.pay(orderId, paymentRequestDtos);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Cancela um pedido por id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Pedido cancelado com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para cancelar pedido"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar cancelamento de pedido"),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long orderId) {
        orderService.cancel(orderId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Sai com um pedido para entrega")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Pedido despachado com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para despachar pedido"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar despachar pedido"),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PutMapping("/{orderId}/dispatch")
    public ResponseEntity<Void> dispatch(@PathVariable Long orderId) {
        orderService.dispatch(orderId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Pedido entregue")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Pedido entregue com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para entregar pedido"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar entrega de pedido"),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PutMapping("/{orderId}/delivery")
    public ResponseEntity<Void> delivery(@PathVariable Long orderId) {
        orderService.delivery(orderId);
        return ResponseEntity.noContent().build();
    }

}
