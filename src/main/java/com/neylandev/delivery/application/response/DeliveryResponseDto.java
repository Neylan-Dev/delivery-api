package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neylandev.delivery.domain.enums.DeliveryStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponseDto {

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
    @ApiModelProperty(value = "Taxa de entrega", name = "tax", dataType = "BigDecimal", example = "20.0")
    private BigDecimal tax;
    @ApiModelProperty(value = "Status da entrega", name = "deliveryStatus", dataType = "DeliveryStatus", example = "PENDING")
    private DeliveryStatus deliveryStatus;
    @ApiModelProperty(value = "Data do pedido", name = "orderedDate", dataType = "OffsetDateTime", example = "2022-07-28T11:00:03.831798-03:00")
    private OffsetDateTime orderedDate;
    @ApiModelProperty(value = "Data da finalização ou cancelamento", name = "endDate", dataType = "OffsetDateTime", example = "2022-07-28T11:00:03.831798-03:00")
    private OffsetDateTime endDate;
}
