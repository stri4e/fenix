package com.github.bitcoin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "currency", schema = "public")
public class Currency implements Serializable, Cloneable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "full_name",
            nullable = false
    )
    private String fullName;

    @Column(
            name = "address_regex",
            nullable = false
    )
    private String addressRegex;

    @Column(
            name = "pow",
            nullable = false
    )
    private Integer pow;

    @Column(
            name = "status",
            nullable = false
    )
    @Enumerated(value = EnumType.STRING)
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

    public Currency(String name, String fullName, String addressRegex, Integer pow) {
        this.name = name;
        this.fullName = fullName;
        this.addressRegex = addressRegex;
        this.pow = pow;
    }

    public Currency(Long id, String name, String fullName, String addressRegex, Integer pow) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.addressRegex = addressRegex;
        this.pow = pow;
    }

}
