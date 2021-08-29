package com.github.products.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specification_sections", schema = "public")
public class SpecSection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "specSections",
            cascade = CascadeType.MERGE,
            targetEntity = Specification.class
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Specification> specifications;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            targetEntity = Product.class
    )
    @JoinColumn(
            nullable = false,
            name = "product_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "product_spec_section_fk"
            )
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Product product;

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

    public SpecSection(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public SpecSection addProduct(Product product) {
        if (Objects.nonNull(product)) {
            this.product = product;
            product.addSpecSection(this);
        }
        return this;
    }

    public SpecSection addSpecifications(List<Specification> specifications) {
        if (Objects.nonNull(specifications)) {
            this.specifications.addAll(specifications);
        }
        return this;
    }

    public SpecSection addSpecification(Specification specification) {
        if (Objects.nonNull(specification)) {
            this.specifications.add(specification);
        }
        return this;
    }

}
