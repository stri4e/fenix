package com.github.payments.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rates", schema = "public")
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
            precision = 32,
            scale = 13,
            columnDefinition="DECIMAL(32, 13)",
            nullable = false
    )
    private Double rate;

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

    public Rate(String time, String assetNameQuote, Double rate, EntityStatus status) {
        this.time = time;
        this.assetNameQuote = assetNameQuote;
        this.rate = rate;
        this.status = status;
    }

    public Rate(String time, String assetNameQuote, Double rate) {
        this.time = time;
        this.assetNameQuote = assetNameQuote;
        this.rate = rate;
    }

}
