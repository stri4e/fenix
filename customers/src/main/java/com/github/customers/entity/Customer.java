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
                query = "select c from Customer c"
        ),
        @NamedQuery(
                name = "Customer.findById",
                query = "select c from Customer c where c.id =:id"
        )
})
@Table(
        name = "customers",
        schema = "public",
        indexes = @Index(
                columnList = "user_id",
                name = "customer_user_id_idx"
        ),
        uniqueConstraints = @UniqueConstraint(
                name = "uk_customer_user_id",
                columnNames = "user_id"
        )
)
public class Customer implements Serializable, Cloneable {

    private static final long serialVersionUID = -6968351368477077711L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "ID"
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            length = 150
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            length = 150
    )
    private String lastName;

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
            nullable = false
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
                    String firstName,
                    String lastName,
                    String customerEmail,
                    String customerPhone,
                    UUID userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.userId = userId;
    }

    public Customer address(Address address) {
        this.address = address;
        return this;
    }

}
