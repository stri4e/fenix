package com.github.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "brances_id"
    )
    private Set<Branch> branches = new HashSet<>();

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private Set<String> cities = new HashSet<>();

    @Column(
            name = "home_price",
            nullable = false
    )
    private BigDecimal homePrice;

    @Column(
            name = "branch_price",
            nullable = false
    )
    private BigDecimal branchPrice;

    @Column(
            name = "status",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private CompanyStatus status;

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
            BigDecimal homePrice, BigDecimal branchPrice) {
        this.id = id;
        this.name = name;
        this.branches = branches;
        this.cities = cities;
        this.homePrice = homePrice;
        this.branchPrice = branchPrice;
    }
}
