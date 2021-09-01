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
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(value = {
        @NamedQuery(
                name = "Criteria.findById",
                query = "select c from Criteria c where c.id=:id"
        )
})
@Table(name = "criteria", schema = "public")
public class Criteria implements Serializable {

    private static final long serialVersionUID = 8592780795812353558L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "value",
            nullable = false,
            length = 100
    )
    private String value;

    @ManyToOne(
            targetEntity = Filter.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "criteria_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "filters_criteria_fk"
            )
    )
    private Filter filter;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            targetEntity = Product.class
    )
    @JoinTable(
            name = "products_criteria",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(
                            name = "products_criteria_fk"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "criteria_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(
                            name = "criteria_products_fk"
                    )
            )
    )
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

    public Criteria(String value) {
        this.value = value;
    }

    public Criteria(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Criteria addFilter(Filter filter) {
        if (Objects.nonNull(filter)) {
            this.filter = filter;
            this.filter.addCriteria(this);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Criteria criteria = (Criteria) o;
        return Objects.equals(id, criteria.id) &&
                Objects.equals(value, criteria.value) &&
                status == criteria.status &&
                Objects.equals(createAt, criteria.createAt) &&
                Objects.equals(updateAt, criteria.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, status, createAt, updateAt);
    }

    public void addProduct(Product product) {
        if (Objects.nonNull(product)) {
            this.products.add(product);
        }
    }

}
