package com.neylandev.delivery.application.request;

import com.neylandev.delivery.domain.enums.BrazilianState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    @NotNull(message = "O campo clientId não pode ser nulo")
    @Schema(description = "Id do cliente", example = "1")
    private Long clientId;
    @NotBlank(message = "O campo recipientName não pode ser nulo")
    @Schema(description = "Nome do destinatário", example = "José da Silva")
    private String recipientName;
    @NotBlank(message = "O campo recipientStreet não pode ser nulo")
    @Schema(description = "Rua do destinatário", example = "Rua do Meio")
    private String recipientStreet;
    @NotBlank(message = "O campo recipientNumber não pode ser nulo")
    @Schema(description = "Numero do endereço do destinatário", example = "10")
    private String recipientNumber;
    @Schema(description = "Complemento do endereço do destinatário", example = "Apto")
    private String recipientComplement;
    @NotBlank(message = "O campo recipientNeighborhood não pode ser nulo")
    @Schema(description = "Bairro do destinatário", example = "Centro")
    private String recipientNeighborhood;
    @NotBlank(message = "O campo recipientZipCode não pode ser nulo")
    @Schema(description = "CEP do destinatário", example = "4520156")
    private String recipientZipCode;
    @NotBlank(message = "O campo recipientCity não pode ser nulo")
    @Schema(description = "Cidade do destinatário", example = "São Paulo")
    private String recipientCity;
    @NotBlank(message = "O campo recipientState não pode ser nulo")
    @Schema(description = "Estado do destinatário", example = "SP")
    private BrazilianState recipientState;
    @NotBlank(message = "O campo recipientCountry não pode ser nulo")
    @Schema(description = "País do destinatário", example = "Brasil")
    private String recipientCountry;
    @NotNull(message = "O campo shipping não pode ser nulo")
    @Schema(description = "Taxa de envio", example = "20.0")
    private BigDecimal shipping;
    @NotNull(message = "O campo orderItemRequestDtos não pode ser nulo")
    @NotEmpty(message = "A lista orderItemRequestDtos não pode ser vazia")
    @Schema(description = "Lista de itens", example = "[{\"productRequestDto\": {\"id\":1}, \"quantity\": 3}]")
    private List<OrderItemRequestDto> orderItemRequestDtos;
    @NotNull(message = "O campo paymentRequestDtos não pode ser nulo")
    @NotEmpty(message = "A lista paymentRequestDtos não pode ser vazia")
    @Schema(description = "Lista de pagamentos", example = "[{\"amount\": 20.0, \"paymentType\": \"PIX\", \"expirationDate\": \"01-01-2023\", \"pixKey\": \"73982342213\"}]")
    private List<PaymentRequestDto> paymentRequestDtos;
}
