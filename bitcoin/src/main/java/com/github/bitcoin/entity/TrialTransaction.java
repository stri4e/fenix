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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trial_transaction", schema = "public")
public class TrialTransaction implements Serializable, Cloneable {

    private static final long serialVersionUID = -7686854354020609007L;

    @Id
    @Column(
            name = "ID"
    )
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
            name = "value",
            nullable = false
    )
    private BigInteger value = BigInteger.ZERO;

    @Column(
            name = "change",
            nullable = false
    )
    private BigInteger change = BigInteger.ZERO;

    @Column(
            name = "fee",
            nullable = false
    )
    private BigInteger fee = BigInteger.ZERO;

    @Column(
            name = "address_from",
            nullable = false
    )
    private String from;

    @Column(
            name = "address_to",
            nullable = false
    )
    private String to;

    @Column(
            name = "signed_trx",
            nullable = false
    )
    private String signedTrx;

    @OneToMany(
            targetEntity = UnspentOut.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "out_id",
            foreignKey = @ForeignKey(
                    name = "trial_trx_unspent_out"
            )
    )
    private List<UnspentOut> unspentOuts = new ArrayList<>();

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

    public TrialTransaction(String hash, BigInteger value, BigInteger fee, BigInteger change,
                            String from, String to, String signedTrx, List<UnspentOut> unspentOuts) {
        this.hash = hash;
        this.value = value;
        this.change = change;
        this.fee = fee;
        this.from = from;
        this.to = to;
        this.signedTrx = signedTrx;
        this.unspentOuts = unspentOuts;
    }

}
