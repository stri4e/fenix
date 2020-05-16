package com.github.users.center.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(value = {
        @NamedQuery(
                name = "Role.findAll",
                query = "SELECT r FROM Role r"
        ),
        @NamedQuery(
                name = "Role.findById",
                query = "SELECT r FROM Role r WHERE r.id =:id"
        )
})
@Table(name = "role", schema = "public")
public class Role implements Serializable, Cloneable {

    private static final long serialVersionUID = -1441562692910494641L;

    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "role",
            nullable = false
    )
    @NotBlank(
            message = "Role is Required"
    )
    private String role;

    public Role(@NotBlank(message = "Role is Required") String role) {
        this.role = role;
    }

}
