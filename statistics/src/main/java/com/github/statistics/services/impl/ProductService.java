package com.github.statistics.services.impl;

import com.github.statistics.dto.ProductDto;
import com.github.statistics.services.IProductService;
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
