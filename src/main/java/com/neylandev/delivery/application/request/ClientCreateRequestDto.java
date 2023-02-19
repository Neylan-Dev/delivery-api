package com.neylandev.delivery.application.request;

import com.neylandev.delivery.domain.enums.AccountType;
import com.neylandev.delivery.domain.enums.BrazilianState;
import com.neylandev.delivery.domain.validator.Name;
import com.neylandev.delivery.domain.validator.Telephone;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateRequestDto {

    @NotBlank(message = "O nome não pode ser nulo")
    @Size(min = 3, max = 100, message = "O nome não deve ter menos que {min} ou mais que {max} caracteres")
    @Name
    @ApiModelProperty(value = "Nome do cliente", name = "name", dataType = "String", example = "Antônio dos Santos")
    private String name;

    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "O email='${validatedValue}' é inválido")
    @ApiModelProperty(value = "Email do cliente", name = "email", dataType = "String", example = "antonio@email.com")
    private String email;

    @NotBlank(message = "O telefone não pode ser nulo")
    @Telephone
    @ApiModelProperty(value = "Telefone do cliente", name = "telephone", dataType = "String", example = "73981234356")
    private String telephone;

    @NotBlank(message = "O campo addressStreet não pode ser nulo")
    @ApiModelProperty(value = "Rua do cliente", name = "addressStreet", dataType = "String", example = "Rua do Meio")
    private String addressStreet;

    @NotBlank(message = "O campo addressNumber não pode ser nulo")
    @ApiModelProperty(value = "Numero do endereço do cliente", name = "addressNumber", dataType = "String", example = "10")
    private String addressNumber;

    @ApiModelProperty(value = "Complemento do endereço do cliente", name = "addressComplement", dataType = "String", example = "Apto")
    private String addressComplement;

    @NotBlank(message = "O campo addressNeighborhood não pode ser nulo")
    @ApiModelProperty(value = "Bairro do cliente", name = "addressNeighborhood", dataType = "String", example = "Centro")
    private String addressNeighborhood;

    @NotBlank(message = "O campo addressZipCode não pode ser nulo")
    @ApiModelProperty(value = "CEP do cliente", name = "addressZipCode", dataType = "String", example = "4520156")
    private String addressZipCode;

    @NotBlank(message = "O campo addressCity não pode ser nulo")
    @ApiModelProperty(value = "Cidade do cliente", name = "addressCity", dataType = "String", example = "São Paulo")
    private String addressCity;


    @NotBlank(message = "O campo addressState não pode ser nulo")
    @ApiModelProperty(value = "Estado do cliente", name = "addressState", dataType = "String", example = "SP")
    private BrazilianState addressState;

    @NotBlank(message = "O campo addressCountry não pode ser nulo")
    @ApiModelProperty(value = "País do cliente", name = "addressCountry", dataType = "String", example = "Brasil")
    private String addressCountry;

    @ApiModelProperty(value = "Agencia da conta do cliente", name = "accountAgency", dataType = "String", example = "001")
    private String accountAgency;

    @ApiModelProperty(value = "Numero da conta do cliente", name = "accountNumber", dataType = "String", example = "2343543546")
    private String accountNumber;

    @ApiModelProperty(value = "Digito da conta do cliente", name = "accountDigit", dataType = "String", example = "1")
    private String accountDigit;

    @ApiModelProperty(value = "Tipo da conta do cliente", name = "accountType", dataType = "String", example = "CURRENT")
    private AccountType accountType;

}
