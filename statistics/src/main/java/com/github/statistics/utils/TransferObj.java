package com.github.statistics.utils;

import com.github.statistics.dto.ProductsStatisticsDto;
import com.github.statistics.dto.UsersStatisticsDto;
import com.github.statistics.entity.ProductsStatistics;
import com.github.statistics.entity.UsersStatistics;

public class TransferObj {

    public static ProductsStatistics toProductsStatistics(ProductsStatisticsDto data) {
        return new ProductsStatistics(
                data.getProductId(),
                data.getLastReviewDate(),
                data.getReviewCount(),
                data.getBoughtCount()
        );
    }

    public static ProductsStatisticsDto fromProductsStatistic(ProductsStatistics data) {
        return new ProductsStatisticsDto(
                data.getId(),
                data.getProductId(),
                data.getLastReviewDate(),
                data.getReviewCount(),
                data.getBoughtCount()
        );
    }

    public static UsersStatisticsDto fromUsersStatisticsDto(UsersStatistics data) {
        return new UsersStatisticsDto(
                data.getId(),
                data.getUserId(),
                data.getLastLoginDate(),
                data.getCount()
        );
    }

}
