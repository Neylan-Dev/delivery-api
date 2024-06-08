package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.ProductRequestDto;
import com.neylandev.delivery.application.response.ProductResponseDto;
import com.neylandev.delivery.domain.enums.Category;
import com.neylandev.delivery.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Cadastra um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para cadastrar os dados de produto"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar cadastro de produto"),
            @ApiResponse(responseCode = "409", description = "Conflito com dados que já estão cadastrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody @Valid ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.save(productRequestDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna todos os produtos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista de produtos"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar busca de produtos"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> listAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @Operation(summary = "Retorna todos os produtos que tem em estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista de produtos"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar busca de produtos"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @GetMapping("/stock")
    public ResponseEntity<List<ProductResponseDto>> listAllInStock() {
        return ResponseEntity.ok(productService.findProductsThatStockGreaterThanZero());
    }

    @Operation(summary = "Busca um produto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "400", description = "Má solicitação para buscar os dados de produto"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar a busca de produto por id"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @Operation(summary = "Retorna todas as categorias de produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista de categorias"),
            @ApiResponse(responseCode = "401", description = "Ausência de autorização"),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado a realizar busca de categorias"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível")
    })
    @GetMapping("/categories")
    public ResponseEntity<List<String>> listAllCategories() {
        return ResponseEntity.ok(Category.getPortugueseCategories());
    }
}
