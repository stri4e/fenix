package com.github.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {

    private Integer discountPercent;

    private BigDecimal discountValue;

    private BigDecimal minimalOrderValue;

    private BigDecimal maxOrderValue;

    private String couponCode;

    private LocalDate startDate;

    private LocalTime startTime;

    private Date endDate;

    private LocalTime endTime;

}
