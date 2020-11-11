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
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery", schema = "public")
public class Delivery implements Serializable, Cloneable {

    private static final long serialVersionUID = 1655627397181362589L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Enumerated(
            value = EnumType.STRING
    )
    @Column(
            name = "delivery_type",
            nullable = false
    )
    private DeliveryType type;

    @Column(
            name = "company_name",
            nullable = false,
            length = 100
    )
    private String companyName;

    @Column(
            name = "address",
            length = 100
    )
    private String address;

    @Column(
            name = "amount",
            precision = 8,
            scale = 4,
            columnDefinition = "DECIMAL(8, 4)",
            nullable = false
    )
    private BigDecimal amount;

    @Column(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private UUID userId;

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

    public Delivery(Long id, DeliveryType type, String companyName, String address, BigDecimal amount, UUID userId) {
        this.id = id;
        this.type = type;
        this.companyName = companyName;
        this.address = address;
        this.amount = amount;
        this.userId = userId;
    }

}
