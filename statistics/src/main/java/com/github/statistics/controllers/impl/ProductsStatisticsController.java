package com.github.statistics.controllers.impl;

import com.github.statistics.controllers.IProductsStatisticsController;
import com.github.statistics.dto.ProductsStatisticsDto;
import com.github.statistics.entity.ProductsStatistics;
import com.github.statistics.services.IProductsStatisticsService;
import com.github.statistics.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(path = "/v1/product")
@RequiredArgsConstructor
public class ProductsStatisticsController implements IProductsStatisticsController {

    private final IProductsStatisticsService productsService;

    @Override
    public ProductsStatisticsDto create(Long payload) {
        var productStatistic = new ProductsStatistics(
                payload, new Date(), 0, 0
        );
        return TransferObj.fromProductsStatistic(productStatistic);
    }

    @Override
    public ProductsStatisticsDto readById(Long statisticId) {
        return TransferObj.fromProductsStatistic(
                this.productsService.readByProductId(statisticId)
        );
    }

    @Override
    public ProductsStatisticsDto readByProductId(Long productId) {
        return TransferObj.fromProductsStatistic(
                this.productsService.readByProductId(productId)
        );
    }

    @Override
    public void updateReview(Long productId) {
        var statistic = this.productsService.readByProductId(productId);
        statistic.setLastReviewDate(new Date());
        statistic.setReviewCount(statistic.getReviewCount() + 1);
        this.productsService.create(statistic);
    }

    @Override
    public void updateBought(Long productId) {
       var statistic = this.productsService.readByProductId(productId);
       statistic.setBoughtCount(statistic.getBoughtCount() + 1);
    }

}
