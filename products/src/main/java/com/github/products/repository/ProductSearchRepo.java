package com.github.products.repository;

import com.github.products.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductSearchRepo {

    Page<Product> keyWorldsSearch(String text);

    Page<Product> fuzzySearch(String text);

}
