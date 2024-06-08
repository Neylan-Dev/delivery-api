package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDto {


    @Schema(description = "Id do item do pedido", example = "1")
    private Long id;
    @Schema(description = "Dados de produto", example = "{\"id\":1, \"name\":\"Caderno 20 Materias\", \"description\":\"Material escolar\", \"price\":20.0, \"category\":\"BOOKS\"}")
    private ProductResponseDto productResponseDto;
    @Schema(description = "Quantidade do produto", example = "1")
    private int quantity;
    @Schema(description = "Preço do item", example = "20.0")
    private BigDecimal subtotal;
}
