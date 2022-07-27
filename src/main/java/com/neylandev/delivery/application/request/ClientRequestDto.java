package com.neylandev.delivery.application.request;

import com.neylandev.delivery.domain.anotations.Name;
import com.neylandev.delivery.domain.anotations.Telephone;
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
    private String name;

    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "O email='${validatedValue}' é inválido")
    private String email;

    @NotBlank(message = "O telefone não pode ser nulo")
    @Telephone
    private String telephone;

}
