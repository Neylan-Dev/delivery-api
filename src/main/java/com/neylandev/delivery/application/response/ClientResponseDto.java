package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neylandev.delivery.domain.enums.AccountType;
import com.neylandev.delivery.domain.enums.BrazilianState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDto {

    @Schema(description = "Id do cliente", example = "1")
    private Long id;
    @Schema(description = "Nome do cliente", example = "Antônio dos Santos")
    private String name;
    @Schema(description = "Email do cliente", example = "antonio@email.com")
    private String email;
    @Schema(description = "Telefone do cliente", example = "73981234356")
    private String telephone;
    @Schema(description = "Rua do cliente", example = "Rua do Meio")
    private String addressStreet;
    @Schema(description = "Numero do endereço do cliente", example = "10")
    private String addressNumber;
    @Schema(description = "Complemento do endereço do cliente", example = "Apto")
    private String addressComplement;
    @Schema(description = "Bairro do cliente", example = "Centro")
    private String addressNeighborhood;
    @Schema(description = "CEP do cliente", example = "4520156")
    private String addressZipCode;
    @Schema(description = "Cidade do cliente", example = "São Paulo")
    private String addressCity;
    @Schema(description = "Estado do cliente", example = "SP")
    private BrazilianState addressState;
    @Schema(description = "País do cliente", example = "Brasil")
    private String addressCountry;
    @Schema(description = "Agencia da conta do cliente", example = "001")
    private String accountAgency;
    @Schema(description = "Numero da conta do cliente", example = "2343543546")
    private String accountNumber;
    @Schema(description = "Digito da conta do cliente", example = "1")
    private String accountDigit;
    @Schema(description = "Tipo da conta do cliente", example = "CURRENT")
    private AccountType accountType;
}
