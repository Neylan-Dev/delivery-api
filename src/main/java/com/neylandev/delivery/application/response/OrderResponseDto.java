package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neylandev.delivery.domain.enums.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "Id da entrega", name = "id", dataType = "Long", example = "1")
    private Long id;
    @ApiModelProperty(value = "Id do cliente", name = "clientId", dataType = "Long", example = "1")
    private Long clientId;
    @ApiModelProperty(value = "Nome do cliente", name = "name", dataType = "String", example = "Antônio dos Santos")
    private String clientName;
    @ApiModelProperty(value = "Email do cliente", name = "email", dataType = "String", example = "antonio@email.com")
    private String clientEmail;
    @ApiModelProperty(value = "Telefone do cliente", name = "telephone", dataType = "String", example = "73981234356")
    private String clientTelephone;
    @ApiModelProperty(value = "Nome do destinatário", name = "recipientName", dataType = "String", example = "José da Silva")
    private String recipientName;
    @ApiModelProperty(value = "Rua do destinatário", name = "recipientStreet", dataType = "String", example = "Rua do Meio")
    private String recipientStreet;
    @ApiModelProperty(value = "Numero do endereço do destinatário", name = "recipientNumber", dataType = "String", example = "10")
    private String recipientNumber;
    @ApiModelProperty(value = "Complemento do endereço do destinatário", name = "recipientComplement", dataType = "String", example = "Apto")
    private String recipientComplement;
    @ApiModelProperty(value = "Bairro do destinatário", name = "recipientNeighborhood", dataType = "String", example = "Centro")
    private String recipientNeighborhood;
    @ApiModelProperty(value = "Lista de itens", name = "orderItemResponseDtos", dataType = "List", example = "[{\"id\":1, \"productResponseDto\": {\"id\":1, \"name\":\"Caderno 20 Materias\", \"description\":\"Material escolar\", \"price\":20.0, \"category\":\"BOOKS\"}, \"quantity\":3, \"subtotal\":60.0}]")
    private List<OrderItemResponseDto> orderItemResponseDtos;
    @ApiModelProperty(value = "Soma de todos os subtotais do orderItemResponseDtos", name = "subtotal", dataType = "BigDecimal", example = "20.0")
    private BigDecimal subtotal;
    @ApiModelProperty(value = "Valor de envio", name = "shipping", dataType = "BigDecimal", example = "20.0")
    private BigDecimal shipping;
    @ApiModelProperty(value = "Valor total do pedido", name = "total", dataType = "BigDecimal", example = "20.0")
    private BigDecimal total;
    @ApiModelProperty(value = "Status do pedido", name = "orderStatus", dataType = "String", example = "PENDING")
    private OrderStatus orderStatus;
    @ApiModelProperty(value = "Data do pedido", name = "orderedDate", dataType = "LocalDateTime", example = "2022-07-28T11:00:03.831798-03:00")
    private LocalDateTime orderedDate;
    @ApiModelProperty(value = "Data da finalização ou cancelamento", name = "endDate", dataType = "LocalDateTime", example = "2022-07-28T11:00:03.831798-03:00")
    private LocalDateTime endDate;
    @ApiModelProperty(value = "Lista de ocorrencias", name = "occurrenceResponseDtos", dataType = "List", example = "[{\"id\":1, \"orderId\":1, \"description\":\"Destinatario não estava na residência\", \"registerDate\":\"2022-07-28T11:00:03.831798-03:00\"}]")
    private List<OccurrenceResponseDto> occurrenceResponseDtos;
    @ApiModelProperty(value = "Lista de pagamentos", name = "paymentResponseDtos", dataType = "List", example = "[{\"id\":1,\"amount\": 20.0, \"paymentType\": \"PIX\", \"expirationDate\": \"01-01-2023\", \"pixKey\": \"73982342213\", \"paymentDate\": \"01-01-2023\"}]")
    private List<PaymentResponseDto> paymentResponseDtos;
}
