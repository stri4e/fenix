package com.github.products.repository;

import com.github.products.entity.ProductStockLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRegistrationRepo extends JpaRepository<ProductStockLink, Long> {
}
