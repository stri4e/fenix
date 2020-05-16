package com.github.users.center.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(value = {
        @NamedQuery(
                name = "User.findAll",
                query = "SELECT u FROM User u"
        ),
        @NamedQuery(
                name = "User.findById",
                query = "SELECT u FROM User u WHERE u.id =:id"
        ),
        @NamedQuery(
                name = "User.findByLogin",
                query = "SELECT u FROM User u WHERE u.login =:login"
        ),
        @NamedQuery(
                name = "User.findByEmail",
                query = "SELECT u FROM User u WHERE u.email =:email"
        ),
        @NamedQuery(
                name = "User.findByEmailOrLogin",
                query = "SELECT u FROM User u WHERE u.email =:email OR u.login =:login"
        )
})
@Table(
        name = "user",
        schema = "public",
        indexes = {
                @Index(columnList = "email", name = "email_idx"),
                @Index(columnList = "login", name = "login_idx")
        }
)
public class User implements Serializable, Cloneable {

    private static final long serialVersionUID = -669272112576179695L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "fName",
            nullable = false
    )
    @NotBlank(
            message = "First name is required."
    )
    private String fName;

    @Column(
            name = "lName",
            nullable = false
    )
    @NotBlank(
            message = "Last name is required."
    )
    private String lName;

    @Column(
            name = "login",
            nullable = false,
            unique = true
    )
    @NotBlank(
            message = "Login is required."
    )
    private String login;

    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    @NotBlank(
            message = "Email is required."
    )
    private String email;

    @Column(
            name = "pass",
            nullable = false
    )
    @NotBlank(
            message = "Password is required."
    )
    private String pass;

    @Column(
            name = "isEnable",
            nullable = false,
            columnDefinition = "boolean default false"
    )
    private boolean isEnable;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )
    )
    private Collection<Role> roles;

}
