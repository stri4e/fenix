package com.github.products.services;

import com.github.products.entity.Criteria;
import com.github.products.entity.Product;
import com.github.products.entity.EntityStatus;
import com.github.products.entity.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface IProductService {

    Page<Product> read(Pageable pageable);

    Page<Product> readAllBySubcategory(
            String subcategory, Pageable pageable
    );

    Page<Product> readByParams(
            String subcategoryName,
            List<Criteria> criteria,
            Pageable pageable
    );

    List<Product> searchProduct(String name, String description);

    Product create(Product p);

    Product readById(Long id);

    List<Product> readAllOff();

    List<Product> readAllByIds(List<Long> ids);

    void update(Product p);

    void updateStatus(EntityStatus status, Long id);

    void updateBoughtCountPlus(Long id);

    void updateBoughtCountMinus(Long id);

}
