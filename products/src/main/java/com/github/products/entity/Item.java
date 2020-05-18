package com.github.products.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@MappedSuperclass
public abstract class Item implements Serializable, Cloneable {

    private static final long serialVersionUID = -7416895101550698061L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(
            name = "price",
            precision = 8,
            scale = 3,
            columnDefinition="DECIMAL(8, 3)"
    )
    private BigDecimal price;

    @Column(
            name = "quantity",
            nullable = false
    )
    private Integer quantity;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @Column(
            name = "preview_image",
            nullable = false
    )
    private String previewImage;

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private List<String> images = new ArrayList<>();

    @Temporal(
            TemporalType.DATE
    )
    @Column(
            name = "create_date",
            updatable = false,
            nullable = false
    )
    private Date createDate;

}
