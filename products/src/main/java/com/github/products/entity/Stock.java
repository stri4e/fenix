package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stocks", schema = "public")
@NamedQueries(value = {
        @NamedQuery(
                name = "Stock.findByStatus",
                query = "select s from Stock s where s.status=:status"
        ),
        @NamedQuery(
                name = "Stock.findById",
                query = "select s from Stock s where s.id=:id"
        ),
        @NamedQuery(
                name = "Stock.findByNameAndNumber",
                query = "select s from Stock s where s.name=:name and s.number=:number"
        )
})
public class Stock implements Serializable {

    private static final long serialVersionUID = 6272521516146573710L;

    @Id
    @Column(name = "id")
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

    @OneToMany(
            mappedBy = "stock",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = StockStaff.class
    )
    private Set<StockStaff> staffs = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "stocks_quantity",
            joinColumns = @JoinColumn(name = "stock_id"),
            foreignKey = @ForeignKey(name = "stock_quantity_product_fk")
    )
    @MapKeyJoinColumn(name = "product_id")
    private Map<Product, Integer> stocksQuantity = new HashMap<>();

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
            Set<StockStaff> staffs,
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
        this.staffs = staffs;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }

    public Stock(
            String name,
            String number,
            String phone,
            String email,
            Set<StockStaff> staffs,
            String country,
            String region,
            String city,
            String street,
            String streetNumber,
            String zipCode) {
        this.name = name;
        this.number = number;
        this.phone = phone;
        this.email = email;
        this.staffs = staffs;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id) &&
                Objects.equals(name, stock.name) &&
                Objects.equals(number, stock.number) &&
                Objects.equals(phone, stock.phone) &&
                Objects.equals(email, stock.email) &&
                Objects.equals(country, stock.country) &&
                Objects.equals(region, stock.region) &&
                Objects.equals(city, stock.city) &&
                Objects.equals(street, stock.street) &&
                Objects.equals(streetNumber, stock.streetNumber) &&
                Objects.equals(zipCode, stock.zipCode) &&
                status == stock.status &&
                Objects.equals(createAt, stock.createAt) &&
                Objects.equals(updateAt, stock.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number, phone, email, country, region, city, street, streetNumber, zipCode, status, createAt, updateAt);
    }
}
