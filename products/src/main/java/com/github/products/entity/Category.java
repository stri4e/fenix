package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraphs(value = {
        @NamedEntityGraph(
                name = "category-find-all-entity-graph",
                attributeNodes = {
                        @NamedAttributeNode(value = "subcategories", subgraph = "subcategory-subgraph")
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "subcategory-subgraph",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "filters", subgraph = "filters-criteria")
                                }
                        ),
                        @NamedSubgraph(
                                name = "filters-criteria",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "criteria")
                                }
                        )
                }
        )
})
@NamedQueries(value = {
        @NamedQuery(
                name = "Category.findAll",
                query = "select c from Category c"
        ),
        @NamedQuery(
                name = "Category.findById",
                query = "select c from Category c where c.id = :id"
        ),
        @NamedQuery(
                name = "Category.findByName",
                query = "select c from Category c where c.name = :name"
        )
})
@Table(
        name = "categories",
        schema = "public",
        indexes = @Index(columnList = "name", name = "category_name_idx"),
        uniqueConstraints = @UniqueConstraint(
                name = "uk_category_name",
                columnNames = "name"
        )
)
public class Category implements Serializable {

    private static final long serialVersionUID = -3168450095167684631L;

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

    @Embedded
    private Discount discount;

    @OneToMany(
            targetEntity = Subcategory.class,
            mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Subcategory> subcategories = new HashSet<>();

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

    public Category(String name) {
        this.name = name;
    }

    public Category(Long id, String previewImage, String name) {
        this.id = id;
        this.previewImage = previewImage;
        this.name = name;
    }

    public Category(Long id, String name, List<Subcategory> subcategories, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.subcategories = new HashSet<>(subcategories);
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Category(String name, List<Subcategory> subcategories, LocalDateTime createAt, LocalDateTime updateAt) {
        this.name = name;
        this.subcategories = new HashSet<>(subcategories);
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(previewImage, category.previewImage) &&
                Objects.equals(name, category.name) &&
                status == category.status &&
                Objects.equals(createAt, category.createAt) &&
                Objects.equals(updateAt, category.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, previewImage, name, status, createAt, updateAt);
    }
}
