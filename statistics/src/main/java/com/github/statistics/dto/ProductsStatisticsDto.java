package com.github.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsStatisticsDto {
    private Long id;
    private Long productId;
    private Date lastReviewDate;
    private Integer reviewCount;
    private Integer boughtCount;
}
