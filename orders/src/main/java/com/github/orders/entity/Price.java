package com.github.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "prices",
        schema = "public"
)
public class Price implements Serializable, Cloneable {

    private static final long serialVersionUID = -8616095426537058805L;

    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "to_home",
            nullable = false
    )
    private BigDecimal toHome;

    @Column(
            name = "to_branch",
            nullable = false
    )
    private BigDecimal toBranch;

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

    public Price(Long id, BigDecimal toHome, BigDecimal toBranch) {
        this.id = id;
        this.toHome = toHome;
        this.toBranch = toBranch;
    }
}
