package com.github.statistics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(
        name = "products_statistics",
        schema = "public"
)
@AllArgsConstructor
@NoArgsConstructor
public class ProductsStatistics implements Serializable, Cloneable {

    private static final long serialVersionUID = -4645377418304960L;

    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "product_id",
            nullable = false,
            unique = true
    )
    private Long productId;

    @Column(
            name = "lastReviewDate",
            nullable = false
    )
    private Date lastReviewDate;

    @Column(
            name = "count",
            nullable = false
    )
    private Integer reviewCount;

    @Column(
            name = "boughtCount",
            nullable = false
    )
    private Integer boughtCount;

    public ProductsStatistics(Long productId, Date lastReviewDate, Integer reviewCount, Integer boughtCount) {
        this.productId = productId;
        this.lastReviewDate = lastReviewDate;
        this.reviewCount = reviewCount;
        this.boughtCount = boughtCount;
    }

}
