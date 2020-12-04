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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fees", schema = "public")
public class Fee implements Serializable, Cloneable {

    private static final long serialVersionUID = -5778399031489268847L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "fee",
            nullable = false
    )
    private BigInteger fee;

    @Column(
            name = "gas_price",
            nullable = false
    )
    private BigInteger gasPrice;

    @CreationTimestamp
    @Column(
            name = "create_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createAt;

    @Enumerated(
            value = EnumType.STRING
    )
    private EntityStatus status = EntityStatus.on;

    @UpdateTimestamp
    @Column(
            name = "update_at",
            nullable = false
    )
    private LocalDateTime updateAt;

    public Fee(BigInteger fee, BigInteger gasPrice, EntityStatus status) {
        this.fee = fee;
        this.gasPrice = gasPrice;
        this.status = status;
    }

    public Fee(BigInteger fee, BigInteger gasPrice) {
        this.fee = fee;
        this.gasPrice = gasPrice;
    }

}
