package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.ProductRequestDto;
import com.neylandev.delivery.application.response.ProductResponseDto;
import com.neylandev.delivery.domain.enums.Category;
import com.neylandev.delivery.domain.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Api(value = "/products/", tags = "Endpoints responsáveis por gerenciar os dados de produtos")
public class ProductController {

    private final ProductService productService;

    @ApiOperation(value = "Cadastra um novo produto", response = ProductResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Má solicitação para cadastrar os dados de produto"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar cadastro de produto"),
            @ApiResponse(code = 409, message = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody @Valid ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.save(productRequestDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retorna todos os produtos cadastrados", response = ProductResponseDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornando lista de produtos"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar busca de produtos"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> listAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @ApiOperation(value = "Retorna todos os produtos que tem em estoque", response = ProductResponseDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornando lista de produtos"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar busca de produtos"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @GetMapping("/stock")
    public ResponseEntity<List<ProductResponseDto>> listAllInStock() {
        return ResponseEntity.ok(productService.findProductsThatStockGreaterThanZero());
    }

    @ApiOperation(value = "Busca um produto por id", response = ProductResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto encontrado"),
            @ApiResponse(code = 400, message = "Má solicitação para buscar os dados de produto"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar a busca de produto por id"),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @ApiOperation(value = "Retorna todas as categorias de produtos", response = Category.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornando lista de categorias"),
            @ApiResponse(code = 401, message = "Ausência de autorização"),
            @ApiResponse(code = 403, message = "Usuário não autorizado a realizar busca de categorias"),
            @ApiResponse(code = 500, message = "Sistema indisponível")
    })
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> listAllCategories() {
        return ResponseEntity.ok(List.of(Category.values()));
    }
}
