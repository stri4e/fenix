package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stocks", schema = "public")
public class Stock implements Serializable, Cloneable {

    private static final long serialVersionUID = 6272521516146573710L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "number",
            nullable = false
    )
    private String number;

    @Column(
            name = "phone",
            nullable = false
    )
    private String phone;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> staffNames = new HashSet<>();

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

    public Stock(
            Long id,
            String name,
            String number,
            String phone,
            String email,
            Set<String> staffNames,
            String country,
            String region,
            String city,
            String street,
            String streetNumber,
            String zipCode) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.phone = phone;
        this.email = email;
        this.staffNames = staffNames;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }

}
