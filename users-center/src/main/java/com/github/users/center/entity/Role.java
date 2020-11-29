package com.github.users.center.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "roles", schema = "public")
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
            length = 50,
            nullable = false
    )
    @NotBlank(
            message = "Role is Required"
    )
    private String role;

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
            nullable = false,
            updatable = false
    )
    private LocalDateTime updateAt;

    public Role(@NotBlank(message = "Role is Required") String role) {
        this.role = role;
    }

}
