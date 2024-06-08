package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neylandev.delivery.domain.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    @Schema(description = "Id do produto", example = "1")
    private Long id;
    @Schema(description = "Nome do produto", example = "Caderno 20 Materias")
    private String name;
    @Schema(description = "Descrição do produto", example = "Material escolar")
    private String description;
    @Schema(description = "Preço do produto", example = "20.0")
    private BigDecimal price;
    @Schema(description = "Categoria do produto", example = "BOOKS")
    private Category category;
    @Schema(description = "Url da imagem do produto", example = "https://cdn.pixabay.com/photo/2017/12/28/10/41/book-3044875_960_720.jpg")
    private String imageUrl;
}
