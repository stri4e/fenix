package com.github.products.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "product", schema = "public")
@NamedQueries(value = {
        @NamedQuery(
                name = "Product.findAll",
                query = "SELECT p FROM Product p"
        ),
        @NamedQuery(
                name = "Product.findById",
                query = "SELECT p FROM Product p WHERE p.id = :id"
        )
})
public class Product extends Item implements Serializable, Cloneable {

    private static final long serialVersionUID = -3490371538827798606L;

    @OneToMany(
            targetEntity = Specification.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "specification_id",
            foreignKey = @ForeignKey(
                    name = "product_specification_fk"
            )
    )
    private Set<Specification> specification = new HashSet<>();

    @OneToMany(
            targetEntity = Comment.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "comment_id",
            foreignKey = @ForeignKey(
                    name = "product_comments_fk"
            )
    )
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(
            targetEntity = Category.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            nullable = false,
            name = "categories_id"
    )
    private Category category;

    @Column(name = "publish")
    private boolean publish;

}
