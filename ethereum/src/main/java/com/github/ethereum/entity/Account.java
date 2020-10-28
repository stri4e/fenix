package com.github.ethereum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts", schema = "public")
public class Account implements Serializable, Cloneable {

    private static final long serialVersionUID = -6603378698265422668L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "user_id",
            nullable = false,
            updatable = false
    )
    private UUID userId;

    @Column(
            name = "private_key",
            nullable = false,
            updatable = false,
            unique = true
    )
    private BigInteger privateKey;

    @Column(
            name = "public_key",
            nullable = false,
            updatable = false,
            unique = true
    )
    private BigInteger publicKey;

    @Column(
            name = "address",
            nullable = false,
            updatable = false,
            unique = true
    )
    private String address;

    @Column(
            name = "currency_balance",
            nullable = false
    )
    private BigInteger currencyBalance;

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private Map<String, BigInteger> contractBalance = new HashMap<>();

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

    public Account(
            Long id,
            UUID userId,
            BigInteger privateKey,
            BigInteger publicKey,
            String address,
            BigInteger currencyBalance,
            EntityStatus status) {
        this.id = id;
        this.userId = userId;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.address = address;
        this.currencyBalance = currencyBalance;
        this.status = status;
    }

    public Account(
            UUID userId,
            BigInteger privateKey,
            BigInteger publicKey,
            String address,
            BigInteger currencyBalance,
            EntityStatus status) {
        this.userId = userId;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.address = address;
        this.currencyBalance = currencyBalance;
        this.status = status;
    }

    public void addContractBal(String contractName, BigInteger newBal) {
        if (this.contractBalance.containsKey(contractName)) {
            var bal = this.contractBalance.getOrDefault(contractName, BigInteger.ZERO);
            var result = bal.add(newBal);
            this.contractBalance.put(contractName, result);
        } else {
            this.contractBalance.put(contractName, newBal);
        }
    }

    public void subContractBal(String contractName, BigInteger value, BigInteger fee) {
        if (this.contractBalance.containsKey(contractName)) {
            var bal = this.contractBalance.getOrDefault(contractName, BigInteger.ZERO);
            var result = bal.subtract(value);
            this.contractBalance.put(contractName, result);
            this.currencyBalance = this.currencyBalance.subtract(fee);
        }
    }

    public void addTransaction(Transaction t) {
        if (Objects.nonNull(t)) {
            this.transactions.add(t);
        }
    }

    public void addCurrencyBal(BigInteger value) {
        this.currencyBalance = this.currencyBalance.add(value);
    }

    public void subCurrencyBal(BigInteger value, BigInteger fee) {
        this.currencyBalance = this.currencyBalance.subtract(value.add(fee));
    }

    public void forUpdateCurrency(BigInteger value, Transaction transaction, EntityStatus status) {
        addCurrencyBal(value);
        addTransaction(transaction);
        this.status = status;
    }

    public void forUpdateCurrency(BigInteger value, Transaction transaction) {
        addCurrencyBal(value);
        addTransaction(transaction);
    }

    public void forUpdateContract(String contract, BigInteger value, Transaction transaction, EntityStatus status) {
        addContractBal(contract, value);
        addTransaction(transaction);
        this.status = status;
    }

    public void forUpdateContract(String contract, BigInteger value, Transaction transaction) {
        addContractBal(contract, value);
        addTransaction(transaction);
    }

}
