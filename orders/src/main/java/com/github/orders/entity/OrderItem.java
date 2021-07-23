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
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items", schema = "public")
public class OrderItem implements Serializable, Cloneable {

    public static final long serialVersionUID = 1442733155841937274L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "product_id",
            nullable = false
    )
    private Long productId;

    @Column(
            name = "quantity",
            nullable = false
    )
    private Integer quantity;

    @Column(
            name = "price",
            nullable = false
    )
    private BigDecimal price;

    @Column(
            name = "discount",
            nullable = false
    )
    private BigDecimal discount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

    @ManyToOne(
            targetEntity = OrderDetail.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "order_id")
    private OrderDetail orderDetail;

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

    public OrderItem(Long id, Long productId, Integer quantity, BigDecimal price, BigDecimal discount) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.discount = Objects.isNull(discount) ? BigDecimal.ZERO : discount;
    }

}
