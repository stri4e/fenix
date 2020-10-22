package com.github.payments.entity;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
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
    private BigInteger amount = BigInteger.ZERO;

    @Column(
            name = "amount_paid",
            nullable = false
    )
    private BigInteger amountPaid = BigInteger.ZERO;

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

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private List<String> transfers = new ArrayList<>();

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

    @OneToOne(
            targetEntity = Who.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "who_id",
            foreignKey = @ForeignKey(
                    name = "bill_payment_type_fk"
            )
    )
    private Who who;

    @OneToOne(
            targetEntity = Whom.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "whom_id",
            foreignKey = @ForeignKey(
                    name = "bill_payment_type_fk"
            )
    )
    private Whom whom;

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

    public void forUpdate(EntityStatus status, BigInteger amountPaid, String transfer) {
        this.status = status;
        this.amountPaid = amountPaid;
        this.transfers.add(transfer);
    }

    public Bill forCreate(Who who, Whom whom) {
        this.who = who;
        this.whom = whom;
        return this;
    }

    public Bill forCreate(Asset asset, PaymentTypes paymentType) {
        this.asset = asset;
        this.paymentType = paymentType;
        return this;
    }

    public Bill(Long id, BigInteger amount, BigInteger amountPaid, String address, BillType billType, List<String> transfers) {
        this.id = id;
        this.amount = amount;
        this.amountPaid = amountPaid;
        this.address = address;
        this.transfers = Objects.isNull(transfers) ? Lists.newArrayList() : transfers;
        this.billType = billType;
    }

    public boolean isOther() {
        return this.billType.equals(BillType.other);
    }

}
