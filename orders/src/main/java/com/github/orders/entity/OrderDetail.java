package com.github.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

    @Column(
            name = "userId",
            nullable = false
    )
    private Long userId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne(
            fetch = FetchType.EAGER,
            targetEntity = Manager.class
    )
    @JoinColumn(
            name = "order_id"
    )
    private Manager manager;

    public OrderDetail(
            Customer customer, List<Long> productIds,
            BigDecimal amount, Long userId, OrderStatus status) {
        this.customer = customer;
        this.productIds = productIds;
        this.amount = amount;
        this.userId = userId;
        this.status = status;
    }

    public void init(Customer customer, Long userId) {
        if (Objects.nonNull(customer) && Objects.nonNull(userId)) {
            this.customer = customer;
            this.userId = userId;
        }
    }

}
