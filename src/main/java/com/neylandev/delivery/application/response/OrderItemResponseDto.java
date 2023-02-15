package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDto {



    @ApiModelProperty(value = "Id do item do pedido", name = "id", dataType = "Long", example = "1")
    private Long id;
    @ApiModelProperty(value = "Dados de produto", name = "productResponseDto", dataType = "Object", example = "{\"id\":1, \"name\":\"Caderno 20 Materias\", \"description\":\"Material escolar\", \"price\":20.0, \"category\":\"BOOKS\"}")
    private ProductResponseDto productResponseDto;
    @ApiModelProperty(value = "Quantidade do produto", name = "quantity", dataType = "int", example = "1")
    private int quantity;
    @ApiModelProperty(value = "Preço do item", name = "subtotal", dataType = "BigDecimal", example = "20.0")
    private BigDecimal subtotal;
}
