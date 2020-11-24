package com.github.customers.entity;

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
@Table(name = "customer_statistics", schema = "public")
public class CustomerStatistics implements Serializable, Cloneable {

    private static final long serialVersionUID = 8979034651565551324L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "total_orders",
            nullable = false
    )
    private Integer totalOrders = 0;

    @Column(
            name = "success_orders",
            nullable = false
    )
    private Integer successOrders = 0;

    @Column(
            name = "returned_orders",
            nullable = false
    )
    private Integer returnedOrders = 0;

    @OneToOne(
            targetEntity = Customer.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "customer_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "customer_stat_customer_fk"
            )
    )
    private Customer customer;

    @Enumerated(EnumType.STRING)
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

    public CustomerStatistics(
            Long id,
            Integer totalOrders,
            Integer successOrders,
            Integer returnedOrders,
            Customer customer) {
        this.id = id;
        this.totalOrders = totalOrders;
        this.successOrders = successOrders;
        this.returnedOrders = returnedOrders;
        this.customer = customer;
    }

    public static CustomerStatistics defCustomerStat(Customer customer) {
        return new CustomerStatistics(
            null,
            0,
            0,
            0,
            customer
        );
    }

}
