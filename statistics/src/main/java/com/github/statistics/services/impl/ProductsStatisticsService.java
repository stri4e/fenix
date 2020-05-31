package com.github.statistics.services.impl;

import com.github.statistics.repository.ProductsStatisticsRepository;
import com.github.statistics.services.IProductsStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductsStatisticsService implements IProductsStatisticsService {

    private final ProductsStatisticsRepository productsRepository;


}
