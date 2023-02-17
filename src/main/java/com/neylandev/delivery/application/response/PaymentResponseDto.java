package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neylandev.delivery.domain.enums.PaymentStatus;
import com.neylandev.delivery.domain.enums.PaymentType;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {

    @ApiModelProperty(value = "Id do pagamento", name = "id", dataType = "Long", example = "1")
    private Long id;
    @ApiModelProperty(value = "Valor do pagamento", name = "amount", dataType = "BigDecimal", example = "20.0")
    private BigDecimal amount;
    @ApiModelProperty(value = "Tipo do pagamento", name = "paymentType", dataType = "String", example = "BOOKS")
    private PaymentType paymentType;
    @ApiModelProperty(value = "Numero do cartão", name = "cardNumber", dataType = "String", example = "5394645570508932")
    private String cardNumber;
    @ApiModelProperty(value = "Nome do dono do cartão", name = "cardName", dataType = "String", example = "João da Silva")
    private String cardName;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @ApiModelProperty(value = "Data de expiração", name = "expirationDate", dataType = "LocalDate", example = "01-01-2023")
    private LocalDate expirationDate;
    @ApiModelProperty(value = "Codigo verificador do cartao", name = "cvv", dataType = "String", example = "123")
    private String cvv;
    @ApiModelProperty(value = "Codigo de barras", name = "barCode", dataType = "String", example = "46918875583328552225462674582912252961816964995")
    private String barCode;
    @ApiModelProperty(value = "Chave pix", name = "pixKey", dataType = "String", example = "73982342213")
    private String pixKey;
    @ApiModelProperty(value = "Status do pagamento", name = "paymentStatus", dataType = "String", example = "PENDING")
    private PaymentStatus paymentStatus;
    @ApiModelProperty(value = "Data de pagamento", name = "paymentDate", dataType = "LocalDate", example = "01-01-2023")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate paymentDate;
    @ApiModelProperty(value = "Flag de pagamento", name = "flagPaid", dataType = "Boolean", example = "false")
    private Boolean flagPaid;
}
