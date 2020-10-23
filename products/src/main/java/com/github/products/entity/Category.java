package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
            targetEntity = SubCategory.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "sub_caretory_id",
            foreignKey = @ForeignKey(
                    name = "category_sub_category_fk"
            )
    )
    private List<SubCategory> subCategories = new ArrayList<>();

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
}
