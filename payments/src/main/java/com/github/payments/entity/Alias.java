package com.github.payments.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
        value = @NamedQuery(
                name = "User.findByBill_Id",
                query = "select a from Alias a where a.bill.id=:biilId"
        )
)
@Table(
        name = "aliases",
        schema = "public",
        indexes = @Index(
                columnList = "user_id",
                name = "alias_user_id_idx"
        ),
        uniqueConstraints = @UniqueConstraint(
                name = "uk_alias_user_id_login",
                columnNames = "user_id"
        )
)
public class Alias implements Serializable, Cloneable {

    private static final long serialVersionUID = 377810999923887713L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(
            targetEntity = Bill.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "bill_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "alias_bill_fk"
            )
    )
    private Bill bill;

    @Column(
            name = "user_id",
            nullable = false
    )
    private UUID userId;

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

    public Alias(Bill bill, UUID userId) {
        this.bill = bill;
        this.userId = userId;
    }
}
