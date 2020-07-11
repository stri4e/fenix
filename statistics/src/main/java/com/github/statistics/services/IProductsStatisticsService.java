package com.github.statistics.services;

import com.github.statistics.entity.ProductsStatistics;

import java.util.Date;

public interface IProductsStatisticsService {

    ProductsStatistics create(ProductsStatistics productsStatistics);

    ProductsStatistics readById(Long id);

    ProductsStatistics readByProductId(Long userId);

    void update(ProductsStatistics productsStatistics);

    void update(Long productId, Date lastReviewDate, Integer reviewCount);

    void update(Long productId, Integer boughtCount);

}
