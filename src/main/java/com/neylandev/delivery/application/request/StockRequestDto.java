package com.neylandev.delivery.application.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRequestDto {
    @NotNull(message = "O campo quantity não pode ser nulo")
    @Positive(message = "O valor no campo quantity deve ser positivo")
    @Schema(description = "Quantidade do produto", example = "3")
    private int quantity;
}
