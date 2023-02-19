package com.neylandev.delivery.application.request;

import com.neylandev.delivery.domain.enums.PaymentType;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

    @NotNull(message = "O campo amount não pode ser nulo")
    @ApiModelProperty(value = "Valor do pagamento", name = "amount", dataType = "BigDecimal", example = "20.0")
    private BigDecimal amount;
    @NotNull
    @ApiModelProperty(value = "Tipo do pagamento", name = "paymentType", dataType = "String", example = "BOOKS")
    private PaymentType paymentType;
    @Digits(fraction = 0, integer = 16)
    @ApiModelProperty(value = "Numero do cartão", name = "cardNumber", dataType = "String", example = "5394645570508932")
    private String cardNumber;
    @ApiModelProperty(value = "Nome do dono do cartão", name = "cardName", dataType = "String", example = "João da Silva")
    private String cardName;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @ApiModelProperty(value = "Data de expiração", name = "expirationDate", dataType = "LocalDate", example = "01-01-2023")
    private LocalDate expirationDate;
    @ApiModelProperty(value = "Codigo verificador do cartao", name = "cvv", dataType = "String", example = "123")
    @Size(min = 3, max = 3)
    @Digits(fraction = 0, integer = 3)
    private String cvv;
    @ApiModelProperty(value = "Codigo de barras", name = "barCode", dataType = "String", example = "46918875583328552225462674582912252961816964995")
    private String barCode;
    @ApiModelProperty(value = "Chave pix", name = "pixKey", dataType = "String", example = "73982342213")
    private String pixKey;

}
