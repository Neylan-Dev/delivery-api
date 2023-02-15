package com.neylandev.delivery.application.request;

import com.neylandev.delivery.domain.enums.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    @ApiModelProperty(value = "Id do produto", name = "id", dataType = "Long", example = "1")
    private Long id;
    @ApiModelProperty(value = "Nome do produto", name = "name", dataType = "String", example = "Caderno 20 Matérias")
    private String name;
    @ApiModelProperty(value = "Preço do produto", name = "price", dataType = "BigDecimal", example = "20.0")
    private BigDecimal price;
    @ApiModelProperty(value = "Descrição do produto", name = "description", dataType = "String", example = "Material Escolar")
    private BigDecimal description;
    @ApiModelProperty(value = "Categoria do produto", name = "category", dataType = "String", example = "BOOKS")
    private Category category;
}
