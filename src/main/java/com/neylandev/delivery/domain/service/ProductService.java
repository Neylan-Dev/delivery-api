package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.application.request.ProductRequestDto;
import com.neylandev.delivery.application.response.ProductResponseDto;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.model.Product;
import com.neylandev.delivery.domain.repository.ProductRepository;
import com.neylandev.delivery.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final StockService stockService;

    @Transactional
    public ProductResponseDto save(ProductRequestDto productRequestDto) {

        this.validate(productRequestDto);

        Optional<Product> optionalProduct = productRepository.findByName(productRequestDto.getName());

        if (optionalProduct.isPresent()) {
            throw DataForBusinessException.PRODUCT_EXISTS.asBusinessExceptionWithDescriptionFormatted(productRequestDto.getName());
        }

        Product product = ParseObjects.productRequestDtoToProduct(productRequestDto);

        product = productRepository.save(product);

        stockService.saveStock(product);

        return ParseObjects.productToProductResponseDto(product);
    }


    public List<ProductResponseDto> findAll() {
        return ParseObjects.listProductToListProductResponseDto(productRepository.findAll());
    }

    public ProductResponseDto findById(Long productId) {
        return ParseObjects.productToProductResponseDto(productRepository.findById(productId)
                .orElseThrow(() -> DataForBusinessException.PRODUCT_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(productId))));
    }

    private void validate(ProductRequestDto productRequestDto) {
        if (Objects.isNull(productRequestDto.getName())) {
            throw DataForBusinessException.PRODUCT_NAME_IS_NULL.asBusinessException();
        }
        if (Objects.isNull(productRequestDto.getPrice())) {
            throw DataForBusinessException.PRODUCT_PRICE_IS_NULL.asBusinessException();
        }
        if (Objects.isNull(productRequestDto.getCategory())) {
            throw DataForBusinessException.PRODUCT_CATEGORY_IS_NULL.asBusinessException();
        }
        if (Objects.isNull(productRequestDto.getDescription())) {
            throw DataForBusinessException.PRODUCT_DESCRIPTION_IS_NULL.asBusinessException();
        }
        if (Objects.isNull(productRequestDto.getImageUrl())) {
            throw DataForBusinessException.PRODUCT_IMAGE_URL_IS_NULL.asBusinessException();
        }
    }

    public List<ProductResponseDto> findProductsThatStockGreaterThanZero() {
        return stockService.findProductsThatStockGreaterThanZero();
    }
}
