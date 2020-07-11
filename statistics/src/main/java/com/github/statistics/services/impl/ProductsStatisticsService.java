package com.github.statistics.services.impl;

import com.github.statistics.entity.ProductsStatistics;
import com.github.statistics.exceptions.NotFound;
import com.github.statistics.repository.ProductsStatisticsRepository;
import com.github.statistics.services.IProductsStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductsStatisticsService implements IProductsStatisticsService {

    private final ProductsStatisticsRepository productsRepository;

    @Override
    public ProductsStatistics create(ProductsStatistics productsStatistics) {
        return this.productsRepository.save(productsStatistics);
    }

    @Override
    public ProductsStatistics readById(Long id) {
        return this.productsRepository.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public ProductsStatistics readByProductId(Long userId) {
        return this.productsRepository.findByProductId(userId)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(ProductsStatistics productsStatistics) {
        this.productsRepository.save(productsStatistics);
    }

    @Override
    public void update(Long productId, Date lastReviewDate, Integer reviewCount) {
        this.productsRepository.update(productId, lastReviewDate, reviewCount);
    }

    @Override
    public void update(Long productId, Integer boughtCount) {
        this.productsRepository.update(productId, boughtCount);
    }

}
