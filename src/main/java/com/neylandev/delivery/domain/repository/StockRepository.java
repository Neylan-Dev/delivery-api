package com.neylandev.delivery.domain.repository;

import com.neylandev.delivery.domain.model.Product;
import com.neylandev.delivery.domain.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProduct(Product product);

    List<Stock> findByQuantityGreaterThan(int quantity);

}
