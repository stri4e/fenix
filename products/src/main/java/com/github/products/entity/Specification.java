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
@Table(name = "specification", schema = "public")
@NamedQueries(value = {
        @NamedQuery(
                name = "Specification.findAll",
                query = "SELECT s FROM Specification s"
        ),
        @NamedQuery(
                name = "Specification.findById",
                query = "SELECT s FROM Specification s WHERE s.id = :id"
        ),
        @NamedQuery(
                name = "Specification.findByName",
                query = "SELECT s FROM Specification s WHERE s.name = :name"
        )
})
public class Specification implements Serializable, Cloneable {

    private static final long serialVersionUID = -8426888996223798372L;

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

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

}
