package com.github.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(value = "discountPercent")
    private Integer discountPercent;

    @JsonProperty(value = "discountValue")
    private BigDecimal discountValue;

    @JsonProperty(value = "minimalOrderValue")
    private BigDecimal minimalOrderValue;

    @JsonProperty(value = "maxOrderValue")
    private BigDecimal maxOrderValue;

    @JsonProperty(value = "couponCode")
    private String couponCode;

    @JsonProperty(value = "startDate")
    private LocalDate startDate;

    @JsonProperty(value = "startTime")
    private LocalTime startTime;

    @JsonProperty(value = "endDate")
    private Date endDate;

    @JsonProperty(value = "endTime")
    private LocalTime endTime;

}
