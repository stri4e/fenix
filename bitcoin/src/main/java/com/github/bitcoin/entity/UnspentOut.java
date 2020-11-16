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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "unspent_outputs", schema = "public")
public class UnspentOut implements Serializable, Cloneable {

    private static final long serialVersionUID = -2053752890704154504L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "index",
            nullable = false
    )
    private Integer index;

    @Column(
            name = "pub_key_hash",
            nullable = false
    )
    private String pubKeyHash;

    @Column(
            name = "script",
            nullable = false
    )
    private String script;

    @Column(
            name = "value",
            nullable = false
    )
    private BigInteger value;

    @Column(
            name = "trx_hash",
            nullable = false
    )
    private String trxHash;

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

    public UnspentOut(Integer index, String pubKeyHash, String script, BigInteger value, String trxHash) {
        this.index = index;
        this.pubKeyHash = pubKeyHash;
        this.script = script;
        this.value = value;
        this.trxHash = trxHash;
    }

}
