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
public class Product extends Item implements Serializable {

    private static final long serialVersionUID = -3490371538827798606L;

    @Embedded
    private Proportions proportions;

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = SpecSection.class
    )
    private Set<SpecSection> specSections = new HashSet<>();

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Comment.class
    )
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(
            targetEntity = Criteria.class,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "products_criteria",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "criteria_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(
                            name = "criteria_products_fk"
                    )
            ),
            foreignKey = @ForeignKey(
                    name = "products_criteria_fk"
            )
    )
    private Set<Criteria> criteria = new HashSet<>();

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            targetEntity = Subcategory.class
    )
    @JoinColumn(
            nullable = false,
            name = "subcategory_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "products_subcategory_fk"
            )
    )
    private Subcategory subcategory;

    @ManyToOne(
            targetEntity = Brand.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            nullable = false,
            name = "brand_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "products_brand_fk"
            )
    )
    private Brand brand;

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = ProductStockLink.class
    )
    private Set<ProductStockLink> links = new HashSet<>();

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.off;

    public void addComment(Comment c) {
        if (Objects.nonNull(c)) {
            this.comments.add(c);
        }
    }

    public void addSpecification(SpecSection s) {
        if (Objects.nonNull(s)) {
            this.specSections.add(s);
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

    public Product subcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
        return this;
    }

    public Product brand(Brand brand) {
        this.brand = brand;
        return this;
    }

    public Product addLink(List<ProductStockLink> links) {
        this.links.addAll(links);
        links.forEach(l -> l.setProduct(this));
        return this;
    }

    public Product addSpecSection(List<SpecSection> sections) {
        if (Objects.nonNull(sections)) {
            this.specSections.addAll(sections);
        }
        return this;
    }

    public Product addBrand(Brand brand) {
        if (Objects.nonNull(brand)) {
            this.brand = brand;
        }
        return this;
    }


    public Product addSubcategory(Subcategory subcategory) {
        if (Objects.nonNull(subcategory)) {
            this.subcategory = subcategory;
        }
        return this;
    }

    public Product addAllCriteria(List<Criteria> c) {
        if (Objects.nonNull(c)) {
            this.criteria.addAll(c);
        }
        return this;
    }

}
