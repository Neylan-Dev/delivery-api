package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neylandev.delivery.domain.enums.PaymentStatus;
import com.neylandev.delivery.domain.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Id do pagamento", example = "1")
    private Long id;
    @Schema(description = "Valor do pagamento", example = "20.0")
    private BigDecimal amount;
    @Schema(description = "Tipo do pagamento", example = "BOOKS")
    private PaymentType paymentType;
    @Schema(description = "Numero do cartão", example = "5394645570508932")
    private String cardNumber;
    @Schema(description = "Nome do dono do cartão", example = "João da Silva")
    private String cardName;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Schema(description = "Data de expiração", example = "01-01-2023")
    private LocalDate expirationDate;
    @Schema(description = "Codigo verificador do cartao", example = "123")
    private String cvv;
    @Schema(description = "Codigo de barras", example = "46918875583328552225462674582912252961816964995")
    private String barCode;
    @Schema(description = "Chave pix", example = "73982342213")
    private String pixKey;
    @Schema(description = "Status do pagamento", example = "PENDING")
    private PaymentStatus paymentStatus;
    @Schema(description = "Data de pagamento", example = "01-01-2023")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate paymentDate;
    @Schema(description = "Flag de pagamento", example = "false")
    private Boolean flagPaid;
}
