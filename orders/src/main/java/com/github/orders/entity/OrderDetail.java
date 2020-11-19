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
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(value = {
        @NamedQuery(
                name = "OrderDetail.findAll",
                query = "SELECT o FROM OrderDetail o"
        ),
        @NamedQuery(
                name = "OrderDetail.findById",
                query = "SELECT o FROM OrderDetail o WHERE o.id =:id"
        ),
        @NamedQuery(
                name = "OrderDetail.findByUserId",
                query = "SELECT o FROM OrderDetail o WHERE o.userId =:userId"
        )
})
@Table(
        name = "order_details",
        schema = "public"
)
public class OrderDetail implements Serializable, Cloneable {

    private static final long serialVersionUID = -8001976834441332571L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "customer_id",
            nullable = false
    )
    private Long customerId;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> productIds;

    @Column(
            name = "amount",
            precision = 8,
            scale = 4,
            columnDefinition="DECIMAL(8, 4)",
            nullable = false
    )
    private BigDecimal amount;

    @Column(
            name = "delivery_id",
            nullable = false
    )
    private Long deliveryId;

    @Column(
            name = "user_id",
            nullable = false
    )
    private UUID userId;

    @Column(
            name = "manager_id"
    )
    private UUID managerId;

    @Column(
            name = "bill_id",
            nullable = false
    )
    private Long billId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.open;

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

    public OrderDetail(
            Long customerId, List<Long> productIds,
            BigDecimal amount, UUID userId, Long billId, OrderStatus status) {
        this.customerId = customerId;
        this.productIds = productIds;
        this.amount = amount;
        this.userId = userId;
        this.billId = billId;
        this.status = status;
    }

    public OrderDetail(
            Long customerId, List<Long> productIds,
            BigDecimal amount, Long deliveryId, UUID userId, Long billId, OrderStatus status) {
        this.customerId = customerId;
        this.productIds = productIds;
        this.amount = amount;
        this.deliveryId = deliveryId;
        this.userId = userId;
        this.billId = billId;
        this.status = status;
    }

}
