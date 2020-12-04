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
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
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
public class Subcategory implements Serializable, Cloneable {

    private static final long serialVersionUID = 9085806796284875698L;

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
            targetEntity = Filter.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "filter_title_id",
            foreignKey = @ForeignKey(
                    name = "sub_category_filter_title_fk"
            )
    )
    private Set<Filter> filters = new HashSet<>();

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

    public Subcategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subcategory(String name) {
        this.name = name;
    }

    public void addFilter(Filter filter) {
        if (Objects.nonNull(filter)) {
            this.filters.add(filter);
        }
    }

}
