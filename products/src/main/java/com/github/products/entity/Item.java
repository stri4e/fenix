package com.github.products.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class Item implements Serializable {

    private static final long serialVersionUID = -7416895101550698061L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @DocumentId
    private Long id;

    @Column(
            name = "vin_code",
            nullable = false
    )
    @Field
    private String vinCode;

    @Column(
            name = "name",
            nullable = false,
            length = 100
    )
    @Field
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
    @Field
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(vinCode, item.vinCode) &&
                Objects.equals(name, item.name) &&
                Objects.equals(price, item.price) &&
                Objects.equals(description, item.description) &&
                Objects.equals(previewImage, item.previewImage) &&
                Objects.equals(images, item.images) &&
                Objects.equals(boughtCount, item.boughtCount) &&
                Objects.equals(createAt, item.createAt) &&
                Objects.equals(updateAt, item.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, vinCode, name, price, description,
                previewImage, images, boughtCount, createAt, updateAt
        );
    }
}
