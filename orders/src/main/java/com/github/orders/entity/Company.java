package com.github.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "companies",
        schema = "public"
)
public class Company implements Serializable, Cloneable {

    private static final long serialVersionUID = -3032898280129319548L;

    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            length = 100
    )
    private String name;

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Branch.class
    )
    @JoinColumn(
            name = "brances_id"
    )
    private Set<Branch> branches = new HashSet<>();

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private Set<String> cities = new HashSet<>();

    @OneToOne(
            fetch = FetchType.EAGER,
            targetEntity = Price.class
    )
    @JoinColumn(
            name = "price_id"
    )
    private Price price;

    @Column(
            name = "status",
            nullable = false
    )
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

    public Company(
            Long id, String name,
            Set<Branch> branches, Set<String> cities,
            Price price) {
        this.id = id;
        this.name = name;
        this.branches = branches;
        this.cities = cities;
        this.price = price;
    }

    public void addBranch(Branch b) {
        if (Objects.nonNull(b)) {
            this.branches.add(b);
        }
    }

}
