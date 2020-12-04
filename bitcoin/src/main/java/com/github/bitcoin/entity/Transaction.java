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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions", schema = "public")
public class Transaction implements Serializable, Cloneable {

    private static final long serialVersionUID = -2385997796398711316L;

    @Id
    @Column(
            name = "ID"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "block_height",
            nullable = false
    )
    private Long blockHeight;

    @Column(
            name = "block_hash",
            nullable = false
    )
    private String blockHash;

    @Column(
            name = "hash",
            nullable = false
    )
    private String hash;

    @Column(
            name = "confirmations",
            nullable = false
    )
    private Integer confirmations = 0;

    @Column(
            name = "value",
            nullable = false
    )
    private BigInteger value = BigInteger.ZERO;

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private Set<String> inputs = new HashSet<>();

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private Set<String> outputs = new HashSet<>();

    @Column(
            name = "status",
            nullable = false
    )
    @Enumerated(value = EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

    @Column(
            name = "type",
            nullable = false
    )
    @Enumerated(value = EnumType.STRING)
    private TransactionType type;

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

    public Transaction(Long id, Long blockHeight, String blockHash, String hash,
                       Integer confirmations, Set<String> inputs, Set<String> outputs) {
        this.id = id;
        this.blockHeight = blockHeight;
        this.blockHash = blockHash;
        this.hash = hash;
        this.confirmations = confirmations;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public Transaction(Long blockHeight, String blockHash, String hash,
                       Integer confirmations, Set<String> inputs,
                       Set<String> outputs,TransactionType type) {
        this.blockHeight = blockHeight;
        this.blockHash = blockHash;
        this.hash = hash;
        this.confirmations = confirmations;
        this.inputs = inputs;
        this.outputs = outputs;
        this.type = type;
    }

    public Transaction(String hash, BigInteger value, Set<String> inputs, Set<String> outputs, TransactionType type) {
        this.hash = hash;
        this.value = value;
        this.inputs = inputs;
        this.outputs = outputs;
        this.type = type;
    }

    public void forUpdate(Long blockHeight, String blockHash, Integer confirmations) {
        this.blockHeight = blockHeight;
        this.blockHash = blockHash;
        this.confirmations = confirmations;
    }

}
