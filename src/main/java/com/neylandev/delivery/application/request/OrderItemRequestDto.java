package com.neylandev.delivery.application.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequestDto {

    @NotNull(message = "O campo productRequestDto não pode ser nulo")
    @ApiModelProperty(value = "Dados do produto", name = "productRequestDto", dataType = "Object", example = "{\"id\":1}")
    private ProductRequestDto productRequestDto;
    @NotNull(message = "O campo quantity não pode ser nulo")
    @ApiModelProperty(value = "Quantidade do item", name = "quantity", dataType = "int", example = "3")
    private int quantity;
}
