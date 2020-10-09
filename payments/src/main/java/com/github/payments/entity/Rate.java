package com.github.payments.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "rates", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Rate implements Serializable, Cloneable {

    private static final long serialVersionUID = -1453353242609144283L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "time",
            nullable = false
    )
    private String time;

    @Column(
            name = "asset_name_quote",
            nullable = false
    )
    private String assetNameQuote;

    @Column(
            name = "rate",
            nullable = false
    )
    private Double rate;

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

    public Rate(String time, String assetNameQuote, Double rate, EntityStatus status) {
        this.time = time;
        this.assetNameQuote = assetNameQuote;
        this.rate = rate;
        this.status = status;
    }
}
