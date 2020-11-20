package com.github.managers.services.impl;

import com.github.managers.dto.ProductDto;
import com.github.managers.services.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Override
    public Optional<ProductDto> create(String subcategoryName, String brandName, ProductDto p) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductDto> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<ProductDto>> readAllUnPublish() {
        return Optional.empty();
    }

    @Override
    public Optional<List<ProductDto>> readByIds(List<Long> ids) {
        return Optional.empty();
    }

    @Override
    public void update(ProductDto p) {

    }

    @Override
    public void remove(Long id, String status) {

    }

    @Override
    public void updateBoughtCountMinus(List<Long> payload) {

    }

    @Override
    public void updateBoughtCountMinus(Long productId) {

    }
}
