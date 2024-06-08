package com.neylandev.delivery.application.request;

import com.neylandev.delivery.domain.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

    @NotNull(message = "O campo amount não pode ser nulo")
    @Schema(description = "Valor do pagamento", example = "20.0")
    private BigDecimal amount;
    @NotNull
    @Schema(description = "Tipo do pagamento", example = "BOOKS")
    private PaymentType paymentType;
    @Digits(fraction = 0, integer = 16)
    @Schema(description = "Numero do cartão", example = "5394645570508932")
    private String cardNumber;
    @Schema(description = "Nome do dono do cartão", example = "João da Silva")
    private String cardName;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Schema(description = "Data de expiração", example = "01-01-2023")
    private LocalDate expirationDate;
    @Schema(description = "Codigo verificador do cartao", example = "123")
    @Size(min = 3, max = 3)
    @Digits(fraction = 0, integer = 3)
    private String cvv;
    @Schema(description = "Codigo de barras", example = "46918875583328552225462674582912252961816964995")
    private String barCode;
    @Schema(description = "Chave pix", example = "73982342213")
    private String pixKey;

}
