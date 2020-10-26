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
        schema = "public",
        indexes = @Index(columnList = "userId", name = "order_user_idx")
)
public class OrderDetail implements Serializable, Cloneable {

    private static final long serialVersionUID = -8001976834441332571L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "customer_id",
            nullable = false
    )
    private Customer customer;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> productIds;

    @Column(
            name = "amount",
            precision = 8,
            scale = 3,
            columnDefinition="DECIMAL(8, 3)",
            nullable = false
    )
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "delivery_id",
            nullable = false
    )
    private Delivery delivery;

    @Column(
            name = "user_id",
            nullable = false
    )
    private Long userId;

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
            Long id, Customer customer, List<Long> productIds,
            BigDecimal amount, Long userId, Long billId, OrderStatus status) {
        this.id = id;
        this.customer = customer;
        this.productIds = productIds;
        this.amount = amount;
        this.userId = userId;
        this.billId = billId;
        this.status = status;
    }

    public OrderDetail(
            Customer customer, List<Long> productIds,
            BigDecimal amount, Delivery delivery, Long userId, Long billId, OrderStatus status) {
        this.customer = customer;
        this.productIds = productIds;
        this.amount = amount;
        this.delivery = delivery;
        this.userId = userId;
        this.billId = billId;
        this.status = status;
    }

    public OrderDetail(
            Long id, Customer customer, List<Long> productIds,
            BigDecimal amount, Long userId, OrderStatus status) {
        this.id = id;
        this.customer = customer;
        this.productIds = productIds;
        this.amount = amount;
        this.userId = userId;
        this.status = status;
    }

    public OrderDetail(
            Customer customer, List<Long> productIds,
            BigDecimal amount, Long userId, OrderStatus status) {
        this.customer = customer;
        this.productIds = productIds;
        this.amount = amount;
        this.userId = userId;
        this.status = status;
    }

}
