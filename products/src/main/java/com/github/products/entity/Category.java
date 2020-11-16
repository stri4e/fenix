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
@Table(name = "categories", schema = "public")
@NamedQueries(value = {
        @NamedQuery(
                name = "Category.findAll",
                query = "SELECT c FROM Category c"
        ),
        @NamedQuery(
                name = "Category.findById",
                query = "SELECT c FROM Category c WHERE c.id = :id"
        ),
        @NamedQuery(
                name = "Category.findByName",
                query = "SELECT c FROM Category c WHERE c.name = :name"
        )
})
public class Category implements Serializable, Cloneable {

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
            length = 50,
            unique = true
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
            name = "subcaretory_id",
            foreignKey = @ForeignKey(
                    name = "category_sub_category_fk"
            )
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

    public void  addSubcategory(Subcategory subcategory) {
        if (Objects.nonNull(subcategory)) {
            this.subcategories.add(subcategory);
        }
    }
}
