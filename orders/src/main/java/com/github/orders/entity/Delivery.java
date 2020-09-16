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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery", schema = "public")
public class Delivery implements Serializable, Cloneable {

    private static final long serialVersionUID = 1655627397181362589L;

    @Id
    @Column(name = "id")
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
            name = "branch_address",
            length = 100
    )
    private String branchAddress;

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

    public Delivery(Long id, DeliveryType type, String companyName, String branchAddress) {
        this.id = id;
        this.type = type;
        this.companyName = companyName;
        this.branchAddress = branchAddress;
    }

}
