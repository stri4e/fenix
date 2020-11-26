package com.github.deliveries.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_last_delivery", schema = "public")
public class CustomerLastDelivery implements Serializable, Cloneable {

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

    @OneToOne(
            targetEntity = Address.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "address_id",
            foreignKey = @ForeignKey(
                    name = "customer_address_fk"
            )
    )
    private Address address;

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

    public CustomerLastDelivery(Long id, DeliveryType type, String companyName, UUID userId) {
        this.id = id;
        this.type = type;
        this.companyName = companyName;
        this.userId = userId;
    }

    public CustomerLastDelivery address(Address address) {
        this.address = address;
        return this;
    }

}
