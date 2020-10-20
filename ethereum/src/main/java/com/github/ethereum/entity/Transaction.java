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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions", schema = "public")
public class Transaction implements Serializable, Cloneable {

    private static final long serialVersionUID = -2402664008533667615L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "hash",
            nullable = false
    )
    private String hash;

    @Column(
            name = "nonce",
            nullable = false
    )
    private BigInteger nonce;

    @Column(
            name = "block_hash"
    )
    private String blockHash;

    @Column(
            name = "block_number"
    )
    private BigInteger blockNumber;

    @Column(
            name = "gas_price",
            nullable = false
    )
    private BigInteger gasPrice;

    @Column(
            name = "gas_limit",
            nullable = false
    )
    private BigInteger gasLimit;

    @ManyToOne(
            targetEntity = Contract.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @Column(
            name = "from",
            nullable = false
    )
    private String from;

    @Column(
            name = "to",
            nullable = false
    )
    private String to;

    @Column(
            name = "value",
            nullable = false
    )
    private BigInteger value;

    @Column(
            name = "fee",
            nullable = false
    )
    private BigInteger fee;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private TransactionType type;

    @Column(name = "status")
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

    public Transaction(
            String hash,
            BigInteger nonce,
            String blockHash,
            BigInteger blockNumber,
            BigInteger gasPrice,
            BigInteger gasLimit,
            Contract contract,
            String from,
            String to,
            BigInteger value,
            BigInteger fee,
            TransactionType type) {
        this.hash = hash;
        this.nonce = nonce;
        this.blockHash = blockHash;
        this.blockNumber = blockNumber;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.contract = contract;
        this.from = from;
        this.to = to;
        this.value = value;
        this.fee = fee;
        this.type = type;
    }

    public Transaction(
            String hash,
            BigInteger nonce,
            String blockHash,
            BigInteger blockNumber,
            BigInteger gasPrice,
            BigInteger gasLimit,
            Contract contract,
            String from,
            String to,
            BigInteger value,
            BigInteger fee,
            TransactionType type,
            EntityStatus status) {
        this.hash = hash;
        this.nonce = nonce;
        this.blockHash = blockHash;
        this.blockNumber = blockNumber;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.contract = contract;
        this.from = from;
        this.to = to;
        this.value = value;
        this.fee = fee;
        this.type = type;
        this.status = status;
    }

    public void forUpdate(String blockHash, BigInteger blockNumber, EntityStatus status) {
        this.blockHash = blockHash;
        this.blockNumber = blockNumber;
        this.status = status;
    }

}
