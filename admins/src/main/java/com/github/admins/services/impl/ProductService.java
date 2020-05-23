package com.github.admins.services.impl;

import com.github.admins.payload.Product;
import com.github.admins.payload.ProductStatus;
import com.github.admins.services.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Override
    public Optional<Product> create(Product p) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Product>> readAllUnPublish() {
        return Optional.empty();
    }

    @Override
    public Optional<List<Product>> readByIds(List<Long> ids) {
        return Optional.empty();
    }

    @Override
    public void update(Product p) {

    }

    @Override
    public void updateStatus(Long id, ProductStatus status) {

    }

}
