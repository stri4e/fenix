package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@NamedQueries(value = {
        @NamedQuery(
                name = "Brand.findByName",
                query = "select b from Brand b where b.name=:name"
        ),
        @NamedQuery(
                name = "Brand.findAllByStatus",
                query = "select b from Brand b where b.status=:status"
        )
})
@Table(
        name = "brands",
        schema = "public",
        indexes = @Index(columnList = "name", name = "brand_name_idx"),
        uniqueConstraints = @UniqueConstraint(
                name = "uk_brands_name",
                columnNames = "name"
        )
)
public class Brand implements Serializable {

    private static final long serialVersionUID = 1226440996435440929L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            length = 100
    )
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

    @OneToMany(
            mappedBy = "brand",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Product.class
    )
    private Set<Product> products = new HashSet<>();

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

    public Brand(String name) {
        this.name = name;
    }

    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Brand(Long id, String name, EntityStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
