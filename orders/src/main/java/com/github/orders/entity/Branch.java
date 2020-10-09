package com.github.orders.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "brances",
        schema = "public"
)
public class Branch implements Serializable, Cloneable {

    private static final long serialVersionUID = 125375057451013087L;

    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "number",
            nullable = false
    )
    private Integer number;

    @Column(
            name = "address",
            nullable = false
    )
    private String address;

    @Column(
            name = "phone",
            nullable = false
    )
    private String phone;

    @Column(
            name = "max_weight",
            nullable = false
    )
    private Integer maxWeight;

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
            nullable = false,
            updatable = false
    )
    private LocalDateTime updateAt;

    public Branch(Long id, Integer number, String address, String phone) {
        this.id = id;
        this.number = number;
        this.address = address;
        this.phone = phone;
    }
}
