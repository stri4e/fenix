package com.github.admins.services.impl;

import com.github.admins.dto.ProductDto;
import com.github.admins.services.IProductService;
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
    public void updateStatus(Long id, String status) {

    }
}
