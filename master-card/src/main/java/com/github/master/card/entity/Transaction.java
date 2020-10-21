package com.github.master.card.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions", schema = "public")
public class Transaction implements Serializable, Cloneable {

    private static final long serialVersionUID = -2371739954624963118L;

    @Id
    @Column(
            name = "ID"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "bill_id",
            nullable = false,
            unique = true
    )
    private Long billId;

    @Column(
            name = "transfer_reference",
            nullable = false,
            unique = true
    )
    private String transferReference;

    @Column(
            name = "payment_type",
            nullable = false
    )
    private String paymentType;

    @Column(
            name = "amount",
            nullable = false
    )
    private BigInteger amount;

    @Column(
            name = "currency",
            nullable = false
    )
    private String currency;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "address",
            nullable = false
    )
    private String address;

    @Column(
            name = "city",
            nullable = false
    )
    private String city;

    @Column(
            name = "country",
            nullable = false
    )
    private String country;

    @ManyToOne(
            targetEntity = Account.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "account_id"
    )
    private Account account;

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

    public Transaction(Long billId, String transferReference, String paymentType,
                       BigInteger amount, String currency, String firstName,
                       String lastName, String address, String city,
                       String country, Account account) {
        this.billId = billId;
        this.transferReference = transferReference;
        this.paymentType = paymentType;
        this.amount = amount;
        this.currency = currency;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.account = account;
    }

}
