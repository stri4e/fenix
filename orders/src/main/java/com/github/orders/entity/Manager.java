package com.github.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mangers", schema = "public")
public class Manager {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "manager_id",
            nullable = false,
            updatable = false
    )
    private Long mangerId;

    @Column(
            name = "first_name",
            nullable = false,
            updatable = false,
            length = 100
    )
    private String firstName;

    @Column(
            name = "lasta_name",
            nullable = false,
            updatable = false,
            length = 100
    )
    private String lastName;

    @Temporal(value = TemporalType.DATE)
    @Column(
            name = "create_at",
            updatable = false,
            nullable = false
    )
    private Date createAt;

    public Manager(Long managerId, String firstName, String lastName) {
        this.mangerId = managerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createAt = new Date();
    }
}
