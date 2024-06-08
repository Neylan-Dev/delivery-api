package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neylandev.delivery.domain.enums.BrazilianState;
import com.neylandev.delivery.domain.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    @Schema(description = "Id da entrega", example = "1")
    private Long id;
    @Schema(description = "Id do cliente", example = "1")
    private Long clientId;
    @Schema(description = "Nome do cliente", example = "Antônio dos Santos")
    private String clientName;
    @Schema(description = "Email do cliente", example = "antonio@email.com")
    private String clientEmail;
    @Schema(description = "Telefone do cliente", example = "73981234356")
    private String clientTelephone;
    @Schema(description = "Nome do destinatário", example = "José da Silva")
    private String recipientName;
    @Schema(description = "Rua do destinatário", example = "Rua do Meio")
    private String recipientStreet;
    @Schema(description = "Numero do endereço do destinatário", example = "10")
    private String recipientNumber;
    @Schema(description = "Complemento do endereço do destinatário", example = "Apto")
    private String recipientComplement;
    @Schema(description = "Bairro do destinatário", example = "Centro")
    private String recipientNeighborhood;
    @Schema(description = "CEP do destinatário", example = "4520156")
    private String recipientZipCode;
    @Schema(description = "Cidade do destinatário", example = "São Paulo")
    private String recipientCity;
    @Schema(description = "Estado do destinatário", example = "SP")
    private BrazilianState recipientState;
    @Schema(description = "País do destinatário", example = "Brasil")
    private String recipientCountry;
    @Schema(description = "Lista de itens", example = "[{\"id\":1, \"productResponseDto\": {\"id\":1, \"name\":\"Caderno 20 Materias\", \"description\":\"Material escolar\", \"price\":20.0, \"category\":\"BOOKS\"}, \"quantity\":3, \"subtotal\":60.0}]")
    private List<OrderItemResponseDto> orderItemResponseDtos;
    @Schema(description = "Soma de todos os subtotais do orderItemResponseDtos", example = "20.0")
    private BigDecimal subtotal;
    @Schema(description = "Valor de envio", example = "20.0")
    private BigDecimal shipping;
    @Schema(description = "Valor total do pedido", example = "20.0")
    private BigDecimal total;
    @Schema(description = "Status do pedido", example = "PENDING")
    private OrderStatus orderStatus;
    @Schema(description = "Data do pedido", example = "2022-07-28T11:00:03.831798-03:00")
    private LocalDateTime orderedDate;
    @Schema(description = "Data da finalização ou cancelamento", example = "2022-07-28T11:00:03.831798-03:00")
    private LocalDateTime endDate;
    @Schema(description = "Lista de ocorrencias", example = "[{\"id\":1, \"orderId\":1, \"description\":\"Destinatario não estava na residência\", \"registerDate\":\"2022-07-28T11:00:03.831798-03:00\"}]")
    private List<OccurrenceResponseDto> occurrenceResponseDtos;
    @Schema(description = "Lista de pagamentos", example = "[{\"id\":1,\"amount\": 20.0, \"paymentType\": \"PIX\", \"expirationDate\": \"01-01-2023\", \"pixKey\": \"73982342213\", \"paymentDate\": \"01-01-2023\"}]")
    private List<PaymentResponseDto> paymentResponseDtos;
}
