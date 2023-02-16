package com.neylandev.delivery.application.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRequestDto {
    @NotNull(message = "O campo quantity não pode ser nulo")
    @Positive(message = "O valor no campo quantity deve ser positivo")
    @ApiModelProperty(value = "Quantidade do produto", name = "quantity", dataType = "int", example = "3")
    private int quantity;
}
