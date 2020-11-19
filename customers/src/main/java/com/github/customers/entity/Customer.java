package com.github.customers.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(
                name = "Customer.findAll",
                query = "SELECT c FROM Customer c"
        ),
        @NamedQuery(
                name = "Customer.findById",
                query = "SELECT c FROM Customer c WHERE c.id =:id"
        )
})
@Table(name = "customers", schema = "public")
public class Customer implements Serializable, Cloneable {

    private static final long serialVersionUID = -6968351368477077711L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "ID",
            unique = true
    )
    private Long id;

    @Column(
            name = "customer_name",
            nullable = false
    )
    private String customerName;

    @Column(
            name = "customer_email",
            length = 128,
            nullable = false
    )
    private String customerEmail;

    @Column(
            name = "customer_phone",
            length = 128,
            nullable = false
    )
    private String customerPhone;

    @OneToOne(
            targetEntity = Address.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "address_id",
            foreignKey = @ForeignKey(
                    name = "customer_address_fk"
            )
    )
    private Address address;

    @Column(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private UUID userId;

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

    public Customer(Long id,
                    String customerName,
                    String customerEmail,
                    String customerPhone,
                    UUID userId) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.userId = userId;
    }

    public Customer address(Address address) {
        this.address = address;
        return this;
    }

}
