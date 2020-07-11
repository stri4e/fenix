package com.github.statistics.repository;

import com.github.statistics.entity.ProductsStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ProductsStatisticsRepository extends JpaRepository<ProductsStatistics, Long> {

    Optional<ProductsStatistics> findByProductId(Long productId);

    @Modifying
    @Query(
            value = "update ProductsStatistics set lastReviewDate =:lastReviewDate," +
                    " reviewCount=:reviewCount where productId=:productId"
    )
    void update(
            @Param(value = "product_id") Long productId,
            @Param(value = "lastReviewDate") Date lastReviewDate,
            @Param(value = "reviewCount") Integer reviewCount
    );

    @Modifying
    @Query(
            value = "update ProductsStatistics set boughtCount=:boughtCount" +
                    " where productId=:productId"
    )
    void update(
            @Param(value = "productId") Long productId,
            @Param(value = "boughtCount") Integer boughtCount
    );

}
