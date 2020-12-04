package com.github.accounts.services.impl;

import com.github.accounts.dto.ProductDto;
import com.github.accounts.services.IProductService;
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
