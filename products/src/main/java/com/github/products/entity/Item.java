package com.github.products.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@MappedSuperclass
public abstract class Item implements Serializable {

    private static final long serialVersionUID = -7416895101550698061L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "vin_code",
            nullable = false
    )
    private String vinCode;

    @Column(
            name = "name",
            nullable = false,
            length = 100
    )
    private String name;

    @Column(
            name = "price",
            precision = 8,
            scale = 3,
            columnDefinition="DECIMAL(8, 3)"
    )
    private BigDecimal price;

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
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "images_product_fk"
            )
    )
    private List<String> images = new ArrayList<>();

    @Column(
            name = "bought_count",
            nullable = false
    )
    private Integer boughtCount = 0;

    @CreationTimestamp
    @Column(
            name = "create_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(
            name = "update_at",
            nullable = false
    )
    private LocalDateTime updateAt;

}
