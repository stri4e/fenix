package com.github.orders.entity;

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
@Table(name = "addresses", schema = "public")
public class Address implements Serializable, Cloneable {

    private static final long serialVersionUID = -813949139938700618L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "country",
            length = 128,
            nullable = false
    )
    private String country;

    @Column(
            name = "city",
            length = 128,
            nullable = false
    )
    private String city;

    @Column(
            name = "street",
            length = 128,
            nullable = false
    )
    private String street;

    @Column(
            name = "street_number",
            length = 128,
            nullable = false
    )
    private Integer streetNumber;

    @Column(
            name = "flat_number",
            length = 128
    )
    private Integer flatNumber;

    @Column(
            name = "state",
            length = 128
    )
    private String state;

    @Column(
            name = "zip_code",
            nullable = false
    )
    private Integer zipCode;

    @Enumerated(EnumType.STRING)
    private AddressType type;

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

    public Address(
            Long id,
            String country,
            String city,
            String street,
            Integer streetNumber,
            Integer flatNumber,
            String state,
            Integer zipCode,
            AddressType type,
            UUID userId) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.flatNumber = flatNumber;
        this.state = state;
        this.zipCode = zipCode;
        this.type = type;
        this.userId = userId;
    }

    public Address(
            Long id,
            String country,
            String city,
            String street,
            Integer streetNumber,
            Integer flatNumber,
            String state,
            Integer zipCode,
            UUID userId) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.flatNumber = flatNumber;
        this.state = state;
        this.zipCode = zipCode;
        this.userId = userId;
    }

}
