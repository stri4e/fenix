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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
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
            name = "name",
            nullable = false,
            length = 50
    )
    private String name;

    @OneToMany(
            targetEntity = Subcategory.class,
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "subcategory_id"
    )
    private List<Subcategory> subcategories = new ArrayList<>();

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

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(Long id, String name, List<Subcategory> subcategories, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.subcategories = subcategories;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Category(String name, List<Subcategory> subcategories, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.subcategories = subcategories;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public void addSubcategory(Subcategory subcategory) {
        if (Objects.nonNull(subcategory)) {
            this.subcategories.add(subcategory);
        }
    }
}
