package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_staffs", schema = "public")
public class StockStaff implements Serializable {

    private static final long serialVersionUID = 7989403996523495180L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "stock_staff_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "stock_staff_phones_fk"
            )
    )
    private Set<String> phones;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            targetEntity = Stock.class
    )
    @JoinColumn(
            name = "stock_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "stock_staff_fk"
            )
    )
    private Stock stock;

    public StockStaff(Long id, String firstName, String lastName, String email, Set<String> phones) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockStaff)) return false;
        StockStaff that = (StockStaff) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phones, that.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phones, stock);
    }
}
