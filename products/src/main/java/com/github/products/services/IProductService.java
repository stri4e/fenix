package com.github.products.services;

import com.github.products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    Page<Product> find(Pageable pageable);

    Page<Product> findAllByCategory(
            String category, Pageable pageable
    );

    Product create(Product p);

    Product readById(Long id);

    List<Product> readAllUnPublish();

    List<Product> readAllByIds(List<Long> ids);

    void updateProduct(Product p);

    void updateIsPublish(boolean isPublish, Long id);

}
