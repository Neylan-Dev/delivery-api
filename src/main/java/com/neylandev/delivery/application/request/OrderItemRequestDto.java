package com.neylandev.delivery.application.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequestDto {

    @NotNull(message = "O campo productRequestDto não pode ser nulo")
    @Schema(description = "Dados do produto", example = "{\"id\":1}")
    private ProductRequestDto productRequestDto;
    @NotNull(message = "O campo quantity não pode ser nulo")
    @Schema(description = "Quantidade do item", example = "3")
    private int quantity;
}
