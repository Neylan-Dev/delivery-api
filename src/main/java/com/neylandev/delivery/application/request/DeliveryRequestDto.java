package com.neylandev.delivery.application.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequestDto {

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
    @NotNull(message = "O campo tax não pode ser nulo")
    @ApiModelProperty(value = "Taxa de entrega", name = "tax", dataType = "BigDecimal", example = "20.0")
    private BigDecimal tax;
}
