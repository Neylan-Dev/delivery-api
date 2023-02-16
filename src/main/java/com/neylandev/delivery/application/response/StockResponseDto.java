package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockResponseDto {

    @ApiModelProperty(value = "Id do estoque", name = "id", dataType = "Long", example = "1")
    private Long id;
    @ApiModelProperty(value = "Dados do produto", name = "productId", dataType = "Object", example = "{\"id\":1, \"name\":\"Caderno 20 Materias\", \"description\":\"Material escolar\", \"price\":20.0, \"category\":\"BOOKS\"}")
    private ProductResponseDto productResponseDto;
    @ApiModelProperty(value = "Quantidade do produto", name = "quantity", dataType = "int", example = "3")
    private int quantity;
}
