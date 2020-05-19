package com.github.orders.service.impl;

import com.github.orders.payload.Product;
import com.github.orders.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Override
    public List<Product> readByIds(List<Long> ids) {
        return Collections.emptyList();
    }
}
