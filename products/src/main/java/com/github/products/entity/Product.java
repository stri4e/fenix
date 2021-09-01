package com.github.products.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private Discount discount;

    @Embedded
    private Proportions proportions;

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = SpecificationSection.class
    )
    private Set<SpecificationSection> sections = new HashSet<>();

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

    @ElementCollection
    @CollectionTable(name = "stocks_quantity",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @MapKeyJoinColumn(name = "stock_id")
    private Map<Stock, Integer> stocksQuantity = new HashMap<>();

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.off;

    public void addComment(Comment c) {
        if (Objects.nonNull(c)) {
            this.comments.add(c);
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

    public Product addSubcategory(Subcategory subcategory) {
        if (Objects.nonNull(subcategory)) {
            this.subcategory = subcategory;
            subcategory.addProduct(this);
        }
        return this;
    }

    public Product brand(Brand brand) {
        if (Objects.nonNull(brand)) {
            this.brand = brand;
            brand.addProduct(this);
        }
        return this;
    }

    public Product addSpecSections(List<SpecificationSection> sections) {
        if (Objects.nonNull(sections)) {
            this.sections.addAll(sections);
        }
        return this;
    }

    public void addSpecSection(SpecificationSection section) {
        if (Objects.nonNull(section)) {
            this.sections.add(section);
        }
    }

    public Product addStocksQuantity(Map<Stock, Integer> stocksQuantity) {
        if (Objects.nonNull(stocksQuantity) && !stocksQuantity.isEmpty()) {
            this.stocksQuantity.putAll(stocksQuantity);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return super.equals(o) && Objects.equals(proportions, product.proportions) && status == product.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), proportions, status);
    }
}
