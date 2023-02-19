package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.application.request.StockRequestDto;
import com.neylandev.delivery.application.response.ProductResponseDto;
import com.neylandev.delivery.application.response.StockResponseDto;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.enums.StockMovementType;
import com.neylandev.delivery.domain.model.Product;
import com.neylandev.delivery.domain.model.Stock;
import com.neylandev.delivery.domain.model.StockMovement;
import com.neylandev.delivery.domain.repository.ProductRepository;
import com.neylandev.delivery.domain.repository.StockMovementRepository;
import com.neylandev.delivery.domain.repository.StockRepository;
import com.neylandev.delivery.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public StockResponseDto addStock(Long productId, StockRequestDto stockRequestDto) {

        Product product = getProductById(productId);

        Optional<Stock> optionalStock = getStockByProduct(product);

        if (optionalStock.isEmpty()) {
            optionalStock = Optional.of(saveStock(product));
        }

        optionalStock.get().setQuantity(optionalStock.get().getQuantity() + stockRequestDto.getQuantity());

        stockMovementRepository.save(StockMovement.builder()
                .stock(optionalStock.get())
                .date(LocalDateTime.now())
                .type(StockMovementType.IN)
                .build());

        return ParseObjects.stockToStockResponseDto(optionalStock.get());
    }

    public Stock saveStock(Product product) {
        return stockRepository.save(Stock.builder().product(product).quantity(0).build());
    }

    public StockResponseDto removeStock(Long productId, StockRequestDto stockRequestDto) {

        Product product = getProductById(productId);

        Optional<Stock> optionalStock = getStockByProduct(product);

        if (optionalStock.isEmpty()) {
            throw DataForBusinessException.PRODUCT_NOT_FOUND_IN_STOCK
                    .asBusinessExceptionWithDescriptionFormatted(Long.toString(productId));
        }

        if (optionalStock.get().getQuantity() < stockRequestDto.getQuantity()) {
            throw DataForBusinessException.QUANTITY_PRODUCT_GREATER_THAN_QUANTITY_STOCK
                    .asBusinessExceptionWithDescriptionFormatted(Long.toString(productId));
        }
        optionalStock.get().setQuantity(optionalStock.get().getQuantity() - stockRequestDto.getQuantity());

        stockMovementRepository.save(StockMovement.builder()
                .stock(optionalStock.get())
                .date(LocalDateTime.now())
                .type(StockMovementType.OUT)
                .build());

        return ParseObjects.stockToStockResponseDto(optionalStock.get());
    }

    public List<ProductResponseDto> findProductsThatStockGreaterThanZero() {
        return ParseObjects.listProductToListProductResponseDto(stockRepository
                .findByQuantityGreaterThan(0).stream().map(Stock::getProduct).collect(Collectors.toList()));
    }

    private Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> DataForBusinessException.PRODUCT_NOT_FOUND
                        .asBusinessExceptionWithDescriptionFormatted(Long.toString(productId)));
    }

    private Optional<Stock> getStockByProduct(Product product) {
        return stockRepository.findByProduct(product);
    }
}
