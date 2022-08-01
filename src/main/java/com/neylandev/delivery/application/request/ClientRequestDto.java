package com.neylandev.delivery.application.request;

import com.neylandev.delivery.domain.validator.Name;
import com.neylandev.delivery.domain.validator.Telephone;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {

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

}
