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
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(value = {
        @NamedQuery(
                name = "Filter.findById",
                query = "select f from Filter f where f.id=:id"
        )
})
@Table(name = "filters", schema = "public")
public class Filter implements Serializable {

    private static final long serialVersionUID = 7706390004430335311L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "title",
            nullable = false,
            length = 50
    )
    private String title;

    @OneToMany(
            targetEntity = Criteria.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "criteria_id",
            foreignKey = @ForeignKey(
                    name = "filters_criteria_fk"
            )
    )
    private List<Criteria> criteria = new ArrayList<>();

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

    public Filter(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Filter(String title, List<Criteria> criteria) {
        this.title = title;
        this.criteria = criteria;
    }

    public Filter(Long id, String title, List<Criteria> criteria) {
        this.id = id;
        this.title = title;
        this.criteria = criteria;
    }

    public void addCriteria(Criteria criteria) {
        if (Objects.nonNull(criteria)) {
            this.criteria.add(criteria);
        }
    }

}
