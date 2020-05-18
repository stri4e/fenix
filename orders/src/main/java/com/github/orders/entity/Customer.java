package com.github.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
            name = "id",
            unique = true
    )
    private Long id;

    @Column(
            name = "customer_name",
            nullable = false
    )
    private String customerName;

    @Column(
            name = "customer_address",
            nullable = false
    )
    private String customerAddress;

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

    public Customer(String customerName,
                    String customerAddress,
                    String customerEmail,
                    String customerPhone) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }

}
