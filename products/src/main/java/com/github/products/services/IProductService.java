package com.github.products.services;

import com.github.products.entity.Product;
import com.github.products.entity.EntityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    Page<Product> read(Pageable pageable);

    Page<Product> readAllByCategory(
            String category, Pageable pageable
    );

    List<Product> searchProduct(String name, String description);

    Product create(Product p);

    Product readById(Long id);

    List<Product> readAllUnPublish();

    List<Product> readAllByIds(List<Long> ids);

    void updateProduct(Product p);

    void updateStatus(EntityStatus status, Long id);

}
