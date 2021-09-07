package com.github.products.services;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> searchProduct(String name, String description);

    Product create(Product p);

    Product readById(Long id);

    Product getById(Long id);

    List<Product> readAllByStatusOff();

    List<Product> readAllByIds(List<Long> ids);

    void update(Product p);

    void updateProduct(Product p);

    void updateStatus(EntityStatus status, Long id);

    void updateBoughtCountPlus(Long id);

    void updateBoughtCountMinus(Long id);

}
