package com.algaworks.algalog.domain;

import com.algaworks.algalog.domain.anotations.Telephone;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ClientDto {

    private Long id;

    @NotBlank(message = "O nome não pode ser nulo")
    @Size(max = 60, message = "O tamanho de nome não pode exceder {max} caracteres")
    private String name;

    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "O email='${validatedValue}' é inválido")
    @Size(max = 255, message = "O tamanho de email não pode exceder {max} caracteres")
    private String email;

    @NotBlank(message = "O telefone não pode ser nulo")
    @Telephone
    private String telephone;

}
