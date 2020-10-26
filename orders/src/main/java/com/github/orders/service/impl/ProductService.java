package com.github.orders.service.impl;

import com.github.orders.dto.ProductDto;
import com.github.orders.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Override
    public Optional<List<ProductDto>> readByIds(List<Long> ids) {
        return Optional.empty();
    }
}
