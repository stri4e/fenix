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
            name = "region",
            length = 128
    )
    private String region;

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
    private String streetNumber;

    @Column(
            name = "flat_number",
            length = 50
    )
    private String flatNumber;

    @Column(
            name = "zip_code",
            nullable = false
    )
    private String zipCode;

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
            String region,
            String city,
            String street,
            String streetNumber,
            String flatNumber,
            String zipCode) {
        this.id = id;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.flatNumber = flatNumber;
        this.zipCode = zipCode;
    }

}
