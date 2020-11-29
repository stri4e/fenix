package com.github.users.center.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "users_alias",
        schema = "public",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_users_alias",
                columnNames = "alias"
        )
)
public class UserAlias implements Serializable, Cloneable {

    private static final long serialVersionUID = 377810999923887713L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(
            targetEntity = User.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            nullable = false,
            name = "user_id",
            foreignKey = @ForeignKey(
                    name = "aliases_users_fk"
            )
    )
    private User user;

    @Column(
            name = "alias",
            nullable = false
    )
    private String alias;

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

    public UserAlias(User user, String alias) {
        this.user = user;
        this.alias = alias;
    }
}
