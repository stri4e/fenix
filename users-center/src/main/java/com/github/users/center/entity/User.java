package com.github.users.center.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;
import java.util.function.Predicate;

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
        name = "users",
        schema = "public",
        indexes = {
                @Index(columnList = "email", name = "email_idx"),
                @Index(columnList = "login", name = "login_idx")
        }
)
public class User implements Serializable, Cloneable {

    private static final long serialVersionUID = -669272112576179695L;

    @Id
    @GeneratedValue(
            generator = "UUID"
    )
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(
            name = "fName",
            nullable = false
    )
    private String fName;

    @Column(
            name = "lName",
            nullable = false
    )
    private String lName;

    @Column(
            name = "login",
            nullable = false,
            unique = true
    )
    private String login;

    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            name = "pass",
            nullable = false
    )
    private String pass;

    @Column(
            name = "phone",
            nullable = false
    )
    private String phone;

    @Column(
            name = "is_enable",
            nullable = false,
            columnDefinition = "boolean default false"
    )
    private boolean isEnable;

    @Column(
            name = "is_locked",
            nullable = false,
            columnDefinition = "boolean default false"
    )
    private boolean isLocked;

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

    public User(
            String fName,
            String lName,
            String email,
            String login,
            String pass,
            String phone,
            Collection<Role> roles
    ) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.login = login;
        this.pass = pass;
        this.roles = roles;
        this.phone = phone;
    }

    public boolean isAuth(Predicate<String> passwordEquals) {
        return passwordEquals.test(this.pass) && this.isEnable && !this.isLocked;
    }

}
