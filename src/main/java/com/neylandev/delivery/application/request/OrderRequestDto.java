package com.neylandev.delivery.application.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    @NotNull(message = "O campo clientId não pode ser nulo")
    @ApiModelProperty(value = "Id do cliente", name = "clientId", dataType = "Long", example = "1")
    private Long clientId;
    @NotBlank(message = "O campo recipientName não pode ser nulo")
    @ApiModelProperty(value = "Nome do destinatário", name = "recipientName", dataType = "String", example = "José da Silva")
    private String recipientName;
    @NotBlank(message = "O campo recipientStreet não pode ser nulo")
    @ApiModelProperty(value = "Rua do destinatário", name = "recipientStreet", dataType = "String", example = "Rua do Meio")
    private String recipientStreet;
    @NotBlank(message = "O campo recipientNumber não pode ser nulo")
    @ApiModelProperty(value = "Numero do endereço do destinatário", name = "recipientNumber", dataType = "String", example = "10")
    private String recipientNumber;
    @ApiModelProperty(value = "Complemento do endereço do destinatário", name = "recipientComplement", dataType = "String", example = "Apto")
    private String recipientComplement;
    @NotBlank(message = "O campo recipientNeighborhood não pode ser nulo")
    @ApiModelProperty(value = "Bairro do destinatário", name = "recipientNeighborhood", dataType = "String", example = "Centro")
    private String recipientNeighborhood;
    @NotNull(message = "O campo shipping não pode ser nulo")
    @ApiModelProperty(value = "Taxa de envio", name = "shipping", dataType = "BigDecimal", example = "20.0")
    private BigDecimal shipping;
    @NotNull(message = "O campo orderItemRequestDtos não pode ser nulo")
    @NotEmpty(message = "A lista orderItemRequestDtos não pode ser vazia")
    @ApiModelProperty(value = "Lista de itens", name = "orderItemRequestDtos", dataType = "List", example = "[{\"productRequestDto\": {\"id\":1}, \"quantity\": 3}]")
    private List<OrderItemRequestDto> orderItemRequestDtos;
    @NotNull(message = "O campo paymentRequestDtos não pode ser nulo")
    @NotEmpty(message = "A lista paymentRequestDtos não pode ser vazia")
    @ApiModelProperty(value = "Lista de pagamentos", name = "paymentRequestDtos", dataType = "List", example = "[{\"amount\": 20.0, \"paymentType\": \"PIX\", \"expirationDate\": \"01-01-2023\", \"pixKey\": \"73982342213\"}]")
    private List<PaymentRequestDto> paymentRequestDtos;
}
