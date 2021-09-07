package com.github.products.services.impl;

import com.github.products.entity.Criteria;
import com.github.products.entity.Product;
import com.github.products.repository.ProductRepo;
import com.github.products.services.IProductPaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.products.utils.ProductSpec.*;

@Service
@RequiredArgsConstructor
public class ProductPaginationService implements IProductPaginationService {

    private final ProductRepo productRepo;

    @Override
    @Cacheable(value = "products", unless = "#result.getTotalElements() == 0")
    public Page<Product> read(Pageable pageable) {
        return this.productRepo.findAll(statusOn(), pageable);
    }

    @Override
    @Cacheable(value = "products_by_subcategory", key = "#subcategory")
    public Page<Product>
    readAllBySubcategory(String subcategory, Pageable pageable) {
        return this.productRepo.findAll(bySubcategory(subcategory), pageable);
    }

    @Override
    public Page<Product>
    readByParams(String subcategoryName, List<Criteria> criteria, Pageable pageable) {
        return this.productRepo.findAll(selectCriteriaIn(subcategoryName, criteria), pageable);
    }

}
