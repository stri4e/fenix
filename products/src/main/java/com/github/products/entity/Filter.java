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
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
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

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Subcategory.class,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            nullable = false,
            name = "subcategory_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "subcategory_filter_fk"
            )
    )
    private Subcategory subcategory;

    @OneToMany(
            mappedBy = "filter",
            targetEntity = Criteria.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
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

    public Filter(String title) {
        this.title = title;
    }

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
            criteria.setFilter(this);
        }
    }

    public Filter addCriteria(List<Criteria> criteria) {
        if (Objects.nonNull(criteria) && !criteria.isEmpty()){
            criteria.forEach(this::addCriteria);
        }
        return this;
    }

    public Filter addSub(Subcategory subcategory) {
        if (Objects.nonNull(criteria)) {
            this.subcategory = subcategory;
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return Objects.equals(id, filter.id) &&
                Objects.equals(title, filter.title) &&
                status == filter.status &&
                Objects.equals(createAt, filter.createAt) &&
                Objects.equals(updateAt, filter.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, status, createAt, updateAt);
    }
}
