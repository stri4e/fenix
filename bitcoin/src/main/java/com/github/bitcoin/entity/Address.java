package com.github.bitcoin.entity;

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
@Table(name = "addresses", schema = "public")
public class Address implements Serializable, Cloneable {

    private static final long serialVersionUID = -6803221807367926458L;

    @Id
    @Column(
            name = "ID"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "index",
            nullable = false,
            unique = true
    )
    private Integer index;

    @Column(
            name = "address",
            nullable = false,
            unique = true
    )
    private String address;

    @Column(
            name = "amount",
            nullable = false
    )
    private BigInteger amount = BigInteger.ZERO;

    @ManyToOne(
            targetEntity = Account.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "account_id"
    )
    private Account account;

    @OneToMany(
            targetEntity = UnspentOut.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "outs_id"
    )
    private List<UnspentOut> outs = new ArrayList<>();

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

    public Address(Integer index, String address, Account account) {
        this.index = index;
        this.address = address;
        this.account = account;
    }

    public Address(Long id, Integer index, String address, BigInteger amount) {
        this.id = id;
        this.index = index;
        this.address = address;
        this.amount = amount;
    }

    public void incoming(UnspentOut out, BigInteger value) {
        if (Objects.nonNull(out) && Objects.nonNull(value)) {
            this.outs.add(out);
            this.amount = this.amount.add(value);
        }
    }

    public void outgoing(BigInteger value, BigInteger change) {
        if (Objects.nonNull(value)) {
            this.amount = this.amount.subtract(value).add(change);
        }
    }

}
