package com.github.products.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

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
@SQLDelete(sql = "update filters as f set f.status='off' where f.id=?")
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

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Subcategory.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            nullable = false,
            name = "subcategory_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "subcategory_filter_fk"
            )
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Subcategory subcategory;

    @OneToMany(
            mappedBy = "filter",
            targetEntity = Criteria.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Criteria> criteria = new HashSet<>();

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
        this.criteria = new HashSet<>(criteria);
    }

    public Filter(Long id, String title, List<Criteria> criteria) {
        this.id = id;
        this.title = title;
        this.criteria = new HashSet<>(criteria);
    }

    public void addCriteria(Criteria criteria) {
        if (Objects.nonNull(criteria)) {
            this.criteria.add(criteria);
        }
    }

}
