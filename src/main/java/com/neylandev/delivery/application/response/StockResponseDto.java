package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockResponseDto {

    @Schema(description = "Id do estoque", example = "1")
    private Long id;
    @Schema(description = "Dados do produto", example = "{\"id\":1, \"name\":\"Caderno 20 Materias\", \"description\":\"Material escolar\", \"price\":20.0, \"category\":\"BOOKS\"}")
    private ProductResponseDto productResponseDto;
    @Schema(description = "Quantidade do produto", example = "3")
    private int quantity;
}
