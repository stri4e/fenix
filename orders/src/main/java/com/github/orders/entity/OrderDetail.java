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
import java.util.ArrayList;
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

    @OneToMany(
            targetEntity = OrderItem.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "order_item_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "orders_order_item_fk"
            )
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(
            name = "amount",
            precision = 8,
            scale = 4,
            columnDefinition = "DECIMAL(8, 4)",
            nullable = false
    )
    private BigDecimal amount;

    @Column(
            name = "weight",
            precision = 8,
            scale = 4,
            columnDefinition = "DECIMAL(8, 4)",
            nullable = false
    )
    private BigDecimal weight;

    @Column(
            name = "company",
            nullable = false
    )
    private String company;

    @Column(
            name = "country",
            nullable = false
    )
    private String country;

    @Column(
            name = "region",
            nullable = false
    )
    private String region;

    @Column(
            name = "city",
            nullable = false
    )
    private String city;

    @Column(
            name = "street",
            nullable = false
    )
    private String street;

    @Column(
            name = "street_number",
            nullable = false
    )
    private String streetNumber;

    @Column(
            name = "flat_number",
            nullable = false
    )
    private String flatNumber;

    @Column(
            name = "zip_code",
            nullable = false
    )
    private String zipCode;

    @Column(
            name = "delivery_data",
            nullable = false
    )
    private String deliveryData;

    @Column(
            name = "delivery_amount",
            precision = 8,
            scale = 4,
            columnDefinition = "DECIMAL(8, 4)",
            nullable = false
    )
    private BigDecimal deliveryAmount;

    @Column(
            name = "user_id",
            nullable = false
    )
    private UUID userId;

    @Column(
            name = "staff_id"
    )
    private Long staffId;

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

    //Constructor for tests
    public OrderDetail(
            Long customerId,
            BigDecimal amount,
            BigDecimal weight,
            String company,
            String country,
            String region,
            String city,
            String street,
            String streetNumber,
            String flatNumber,
            String zipCode,
            String deliveryData,
            BigDecimal deliveryAmount,
            UUID userId,
            OrderStatus status
    ) {
        this.customerId = customerId;
        this.amount = amount;
        this.weight = weight;
        this.company = company;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.flatNumber = flatNumber;
        this.zipCode = zipCode;
        this.deliveryData = deliveryData;
        this.deliveryAmount = deliveryAmount;
        this.userId = userId;
        this.status = status;
    }

    public OrderDetail(
            Long customerId,
            List<OrderItem> orderItems,
            BigDecimal amount,
            BigDecimal weight,
            String company,
            String country,
            String region,
            String city,
            String street,
            String streetNumber,
            String flatNumber,
            String zipCode,
            String deliveryData,
            BigDecimal deliveryAmount,
            UUID userId,
            OrderStatus status
    ) {
        this.customerId = customerId;
        this.orderItems = orderItems;
        this.amount = amount;
        this.weight = weight;
        this.company = company;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.flatNumber = flatNumber;
        this.zipCode = zipCode;
        this.deliveryData = deliveryData;
        this.deliveryAmount = deliveryAmount;
        this.userId = userId;
        this.status = status;
    }

}
