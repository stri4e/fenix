package com.github.products.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "products", schema = "public")
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
                    name = "products_specification_fk"
            )
    )
    private Set<Specification> specifications = new HashSet<>();

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

    @ManyToMany(
            targetEntity = Criteria.class,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "products_criteria",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "criteria_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Criteria> criteria = new HashSet<>();

    @ManyToOne(
            targetEntity = Subcategory.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            nullable = false,
            name = "sub_category_id"
    )
    private Subcategory subcategory;

    @ManyToOne(
            targetEntity = Brand.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            nullable = false,
            name = "brand_id"
    )
    private Brand brand;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.off;

    public void addComment(Comment c) {
        if (Objects.nonNull(c)) {
            this.comments.add(c);
        }
    }

    public void addSpecification(Specification s) {
        if (Objects.nonNull(s)) {
            this.specifications.add(s);
        }
    }

    public void addCriteria(Criteria c) {
        if (Objects.nonNull(c)) {
            this.criteria.add(c);
        }
    }

    public void addCriteria(List<Criteria> c) {
        if (Objects.nonNull(c)) {
            this.criteria.addAll(c);
        }
    }

}
