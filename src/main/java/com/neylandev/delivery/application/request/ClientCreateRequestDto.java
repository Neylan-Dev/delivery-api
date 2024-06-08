package com.neylandev.delivery.application.request;

import com.neylandev.delivery.domain.enums.AccountType;
import com.neylandev.delivery.domain.enums.BrazilianState;
import com.neylandev.delivery.domain.validator.Name;
import com.neylandev.delivery.domain.validator.Telephone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateRequestDto {

    @NotBlank(message = "O nome não pode ser nulo")
    @Size(min = 3, max = 100, message = "O nome não deve ter menos que {min} ou mais que {max} caracteres")
    @Name
    @Schema(description = "Nome do cliente", example = "Antônio dos Santos", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "O email='${validatedValue}' é inválido")
    @Schema(description = "Email do cliente", example = "antonio@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "O telefone não pode ser nulo")
    @Telephone
    @Schema(description = "Telefone do cliente", example = "73981234356", requiredMode = Schema.RequiredMode.REQUIRED)
    private String telephone;

    @NotBlank(message = "O campo addressStreet não pode ser nulo")
    @Schema(description = "Rua do cliente", example = "Rua do Meio", requiredMode = Schema.RequiredMode.REQUIRED)
    private String addressStreet;

    @NotBlank(message = "O campo addressNumber não pode ser nulo")
    @Schema(description = "Numero do endereço do cliente", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    private String addressNumber;

    @Schema(description = "Complemento do endereço do cliente", example = "Apto")
    private String addressComplement;

    @NotBlank(message = "O campo addressNeighborhood não pode ser nulo")
    @Schema(description = "Bairro do cliente", example = "Centro", requiredMode = Schema.RequiredMode.REQUIRED)
    private String addressNeighborhood;

    @NotBlank(message = "O campo addressZipCode não pode ser nulo")
    @Schema(description = "CEP do cliente", example = "4520156", requiredMode = Schema.RequiredMode.REQUIRED)
    private String addressZipCode;

    @NotBlank(message = "O campo addressCity não pode ser nulo")
    @Schema(description = "Cidade do cliente", example = "São Paulo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String addressCity;

    @NotBlank(message = "O campo addressState não pode ser nulo")
    @Schema(description = "Estado do cliente", example = "SP", requiredMode = Schema.RequiredMode.REQUIRED)
    private BrazilianState addressState;

    @NotBlank(message = "O campo addressCountry não pode ser nulo")
    @Schema(description = "País do cliente", example = "Brasil", requiredMode = Schema.RequiredMode.REQUIRED)
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
