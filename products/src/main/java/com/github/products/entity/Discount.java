package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
public class Discount implements Serializable {

    private static final long serialVersionUID = -2735798809068289051L;

    @Column(
            name = "discount_percent"
    )
    private Integer discountPercent;

    @Column(
            name = "discount_value",
            precision = 8,
            scale = 3,
            columnDefinition = "DECIMAL(8, 3)"
    )
    private BigDecimal discountValue;

    @Column(
            name = "minimal_order_value",
            precision = 8,
            scale = 3,
            columnDefinition = "DECIMAL(8, 3)"
    )
    private BigDecimal minimalOrderValue;

    @Column(
            name = "max_order_value",
            precision = 8,
            scale = 3,
            columnDefinition = "DECIMAL(8, 3)"
    )
    private BigDecimal maxOrderValue;

    @Column(
            name = "coupon_code",
            length = 150
    )
    private String couponCode;

    @Column(
            name = "start_date",
            columnDefinition = "DATE"
    )
    private LocalDate startDate;

    @Column(
            name = "start_time",
            columnDefinition = "TIMESTAMP"
    )
    private LocalTime startTime;

    @Column(
            name = "end_date",
            columnDefinition = "DATE"
    )
    private Date endDate;

    @Column(
            name = "end_time",
            columnDefinition = "TIMESTAMP"
    )
    private LocalTime endTime;

    public Discount() {
        this.discountPercent = 0;
        this.discountValue = BigDecimal.ZERO;
        this.minimalOrderValue = BigDecimal.ZERO;
        this.maxOrderValue = BigDecimal.ZERO;
        this.couponCode = null;
        this.startDate = null;
        this.startTime = null;
        this.endDate = null;
        this.endTime = null;
    }

    public void reset() {
        this.discountPercent = 0;
        this.discountValue = BigDecimal.ZERO;
        this.minimalOrderValue = BigDecimal.ZERO;
        this.maxOrderValue = BigDecimal.ZERO;
        this.couponCode = null;
        this.startDate = null;
        this.startTime = null;
        this.endDate = null;
        this.endTime = null;
    }

}
