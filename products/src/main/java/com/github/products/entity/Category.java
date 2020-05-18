package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories", schema = "public")
@NamedQueries(value = {
        @NamedQuery(
                name = "Category.findAll",
                query = "SELECT c FROM Category c"
        ),
        @NamedQuery(
                name = "Category.findById",
                query = "SELECT c FROM Category c WHERE c.id = :id"
        ),
        @NamedQuery(
                name = "Category.findByName",
                query = "SELECT c FROM Category c WHERE c.name = :name"
        )
})
public class Category implements Serializable, Cloneable {

    private static final long serialVersionUID = -3168450095167684631L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            length = 50
    )
    private String name;

}
