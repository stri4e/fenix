package com.github.payments.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "current_rates", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class CurrentRate implements Serializable, Cloneable {

    private static final long serialVersionUID = -6795243662542762561L;

    @Id
    @Column(
            name = "ID"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "asset_name",
            nullable = false
    )
    private String assetName;

    @OneToMany
    @JoinColumn(
            name = "rate_id",
            foreignKey = @ForeignKey(
                    name = "current_rates_rate_fk"
            )
    )
    private List<Rate> rates;

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

    public CurrentRate(String assetName, List<Rate> rates) {
        this.assetName = assetName;
        this.rates = rates;
    }
}
