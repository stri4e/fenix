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
@NamedQueries(value = {
        @NamedQuery(
                name = "PaymentTypes.findByAlias",
                query = "select pt from PaymentTypes pt where pt.alias=:alias"
        ),
        @NamedQuery(
                name = "PaymentTypes.findAllByStatus",
                query = "select pt from PaymentTypes pt where pt.status=:status"
        )
})
@Table(
        name = "payment_type",
        schema = "public",
        indexes = @Index(columnList = "alias", name = "payment_types_alias_idx"),
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_payment_types_alias",
                        columnNames = "alias"
                )
        }
)
public class PaymentTypes implements Serializable, Cloneable {

    private static final long serialVersionUID = -4855944889170935433L;

    @Id
    @Column(
            name = "ID"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "alias",
            nullable = false
    )
    private String alias;

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

    public PaymentTypes(Long id, String alias) {
        this.id = id;
        this.alias = alias;
    }
}
