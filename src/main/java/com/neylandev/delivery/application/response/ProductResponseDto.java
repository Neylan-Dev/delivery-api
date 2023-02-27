package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neylandev.delivery.domain.enums.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    @ApiModelProperty(value = "Id do produto", name = "id", dataType = "Long", example = "1")
    private Long id;
    @ApiModelProperty(value = "Nome do produto", name = "name", dataType = "String", example = "Caderno 20 Materias")
    private String name;
    @ApiModelProperty(value = "Descrição do produto", name = "description", dataType = "String", example = "Material escolar")
    private String description;
    @ApiModelProperty(value = "Preço do produto", name = "price", dataType = "BigDecimal", example = "20.0")
    private BigDecimal price;
    @ApiModelProperty(value = "Categoria do produto", name = "category", dataType = "Category", example = "BOOKS")
    private Category category;
    @ApiModelProperty(value = "Url da imagem do produto", name = "imageUrl", dataType = "String", example = "https://cdn.pixabay.com/photo/2017/12/28/10/41/book-3044875_960_720.jpg")
    private String imageUrl;
}
