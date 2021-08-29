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
import java.util.Objects;

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

}
