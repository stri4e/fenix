package com.github.products.services;

import com.github.products.entity.Criteria;
import com.github.products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductPaginationService {

    Page<Product> read(Pageable pageable);

    Page<Product> readAllBySubcategory(
            String subcategory, Pageable pageable
    );

    Page<Product> readByParams(
            String subcategoryName,
            List<Criteria> criteria,
            Pageable pageable
    );

}
