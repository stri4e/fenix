package com.github.products.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@NamedEntityGraphs(
        value = {
                @NamedEntityGraph(
                        name = "subcategory-filter-entity-graph",
                        attributeNodes = @NamedAttributeNode(
                                value = "filters"
                        )
                )
        }
)
@NamedQueries(value = {
        @NamedQuery(
                name = "Subcategory.findById",
                query = "select s from Subcategory s where s.id=:id"
        ),
        @NamedQuery(
                name = "Subcategory.findByName",
                query = "select s from Subcategory s where s.name=:name"
        ),
        @NamedQuery(
                name = "Subcategory.findAllByStatus",
                query = "select s from Subcategory s where s.status=:status"
        )
})
@Table(
        name = "subcategories",
        schema = "public",
        indexes = @Index(columnList = "name", name = "subcategory_name_idx"),
        uniqueConstraints = @UniqueConstraint(
                name = "uk_subcategory_name",
                columnNames = "name"
        )
)
public class Subcategory implements Serializable {

    private static final long serialVersionUID = 9085806796284875698L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "preview_image",
            nullable = false
    )
    private String previewImage;

    @Column(
            name = "name",
            nullable = false,
            length = 50
    )
    private String name;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Category.class,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "subcategory_category_fk"
            )
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Category category;

    @OneToMany(
            mappedBy = "subcategory",
            targetEntity = Filter.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Filter> filters = new HashSet<>();

    @OneToMany(
            mappedBy = "subcategory",
            targetEntity = Specification.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Specification> specifications = new HashSet<>();

    @OneToMany(
            mappedBy = "subcategory",
            targetEntity = Product.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Product> products = new HashSet<>();

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

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

    public Subcategory(String name) {
        this.name = name;
    }

    public Subcategory(Long id, String previewImage, String name) {
        this.id = id;
        this.previewImage = previewImage;
        this.name = name;
    }

    public void addFilter(Filter filter) {
        if (Objects.nonNull(filter)) {
            this.filters.add(filter);
            filter.setSubcategory(this);
        }
    }

    public Subcategory addFilters(List<Filter> filters) {
        if (Objects.nonNull(filters) && !filters.isEmpty()) {
            filters.forEach(this::addFilter);
        }
        return this;
    }

    public Subcategory addCategory(Category category) {
        if (Objects.nonNull(category)) {
            this.category = category;
        }
        return this;
    }

    public Subcategory addSpecification(Specification specification) {
        if (Objects.nonNull(specification)) {
            this.specifications.add(specification);
        }
        return this;
    }

    public void addProduct(Product product) {
        if (Objects.nonNull(product)) {
            this.products.add(product);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subcategory)) return false;
        Subcategory that = (Subcategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(previewImage, that.previewImage) &&
                Objects.equals(name, that.name) &&
                status == that.status &&
                Objects.equals(createAt, that.createAt) &&
                Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, previewImage, name, status, createAt, updateAt);
    }
}
