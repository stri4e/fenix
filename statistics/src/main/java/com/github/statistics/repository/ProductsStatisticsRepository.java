package com.github.statistics.repository;

import com.github.statistics.entity.ProductsStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsStatisticsRepository extends JpaRepository<ProductsStatistics, Long> {
}
