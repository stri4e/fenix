package com.github.statistics.controllers.impl;

import com.github.statistics.controllers.IProductsStatisticsController;
import com.github.statistics.services.IProductsStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductsStatisticsController implements IProductsStatisticsController {

    private final IProductsStatisticsService productsService;

}
