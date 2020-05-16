package com.github.admins.services.impl;

import com.github.admins.payload.Product;
import com.github.admins.payload.ProductStatus;
import com.github.admins.services.IProductService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private static final Product EMPTY = new Product();

    @Override
    public Product create(Product p) {
        return EMPTY;
    }

    @Override
    public Product readById(Long id) {
        return EMPTY;
    }

    @Override
    public List<Product> readAllUnPublish() {
        return Collections.emptyList();
    }

    @Override
    public List<Product> readByIds(List<Long> ids) {
        return Collections.emptyList();
    }

    @Override
    public void update(Product p) {

    }

    @Override
    public void updateStatus(Long id, ProductStatus status) {

    }

}
