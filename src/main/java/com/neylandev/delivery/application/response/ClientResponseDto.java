package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neylandev.delivery.domain.enums.AccountType;
import com.neylandev.delivery.domain.enums.BrazilianState;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDto {

    @ApiModelProperty(value = "Id do cliente", name = "id", dataType = "Long", example = "1")
    private Long id;
    @ApiModelProperty(value = "Nome do cliente", name = "name", dataType = "String", example = "Antônio dos Santos")
    private String name;
    @ApiModelProperty(value = "Email do cliente", name = "email", dataType = "String", example = "antonio@email.com")
    private String email;
    @ApiModelProperty(value = "Telefone do cliente", name = "telephone", dataType = "String", example = "73981234356")
    private String telephone;
    @ApiModelProperty(value = "Rua do cliente", name = "addressStreet", dataType = "String", example = "Rua do Meio")
    private String addressStreet;
    @ApiModelProperty(value = "Numero do endereço do cliente", name = "addressNumber", dataType = "String", example = "10")
    private String addressNumber;
    @ApiModelProperty(value = "Complemento do endereço do cliente", name = "addressComplement", dataType = "String", example = "Apto")
    private String addressComplement;
    @ApiModelProperty(value = "Bairro do cliente", name = "addressNeighborhood", dataType = "String", example = "Centro")
    private String addressNeighborhood;
    @ApiModelProperty(value = "CEP do cliente", name = "addressZipCode", dataType = "String", example = "4520156")
    private String addressZipCode;
    @ApiModelProperty(value = "Cidade do cliente", name = "addressCity", dataType = "String", example = "São Paulo")
    private String addressCity;
    @ApiModelProperty(value = "Estado do cliente", name = "addressState", dataType = "String", example = "SP")
    private BrazilianState addressState;
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
