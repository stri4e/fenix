package com.github.payments.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bills", schema = "public")
public class Bill implements Serializable, Cloneable {

    private static final long serialVersionUID = 7931438791335670681L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "amount",
            nullable = false
    )
    private BigInteger amount;

    @OneToOne
    @JoinColumn(
            name = "asset_id",
            foreignKey = @ForeignKey(
                    name = "asset_bill_fk"
            )
    )
    private Asset asset;

    @Column(
            name = "address",
            nullable = false
    )
    private String address;

    @OneToOne(
            targetEntity = PaymentTypes.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "payment_type_id",
            foreignKey = @ForeignKey(
                    name = "bill_payment_type_fk"
            )
    )
    private PaymentTypes paymentType;

    @Column(
            name = "bill_type",
            nullable = false
    )
    @Enumerated(value = EnumType.STRING)
    private BillType billType;

    @Column(
            name = "status",
            nullable = false
    )
    @Enumerated(value = EnumType.STRING)
    private EntityStatus status;

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

}
