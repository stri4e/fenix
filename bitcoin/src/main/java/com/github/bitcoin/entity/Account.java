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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts", schema = "public")
public class Account implements Serializable, Cloneable {

    private static final long serialVersionUID = 3608765672379710671L;

    @Id
    @Column(
            name = "ID"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "user_id",
            nullable = false,
            updatable = false
    )
    private Long userId;

    @Column(
            name = "mnemonic",
            nullable = false,
            unique = true
    )
    private String mnemonic;

    @Column(
            name = "private_key",
            nullable = false,
            unique = true
    )
    private String privateKey;

    @Column(
            name = "public_key",
            nullable = false,
            unique = true
    )
    private String publicKey;

    @Column(
            name = "chain_code",
            nullable = false,
            unique = true
    )
    private String chainCode;

    @Column(
            name = "time_stamp",
            nullable = false,
            unique = true
    )
    private Long timeStamp;

    @Column(
            name = "amount",
            nullable = false
    )
    private BigInteger amount = BigInteger.ZERO;

    @OneToMany(
            targetEntity = Address.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "address_id",
            foreignKey = @ForeignKey(
                    name = "account_address_fk"
            )
    )
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(
            targetEntity = Transaction.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "transaction_id",
            foreignKey = @ForeignKey(
                    name = "account_transaction_fk"
            )
    )
    private Set<Transaction> transactions = new HashSet<>();

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

    public Account(String mnemonic,
                   String privateKey,
                   String publicKey,
                   String chainCode,
                   Long timeStamp) {
        this.mnemonic = mnemonic;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.chainCode = chainCode;
        this.timeStamp = timeStamp;
    }

    public void incoming(Transaction transaction, Long value) {
        addTransaction(transaction);
        addAmount(value);
    }

    public void addTransaction(Transaction transaction) {
        if (Objects.nonNull(transaction)) {
            this.transactions.add(transaction);
        }
    }

    public void addAmount(Long value) {
        if (Objects.nonNull(value)) {
            this.amount = this.amount.add(BigInteger.valueOf(value));
        }
    }

}
