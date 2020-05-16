package com.github.users.center.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NamedQueries(value = {
        @NamedQuery(
                name = "ConfirmToken.findAll",
                query = "SELECT ct FROM ConfirmToken ct"
        ),
        @NamedQuery(
                name = "ConfirmToken.findByToken",
                query = "SELECT ct FROM ConfirmToken ct WHERE ct.token =:token"
        ),
        @NamedQuery(
                name = "ConfirmToken.findById",
                query = "SELECT ct FROM ConfirmToken ct WHERE ct.id =:id"
        )
})
@Table(
        name = "confirm_token",
        schema = "public",
        indexes = @Index(columnList = "token", name = "confirm_token_idx")
)
public class ConfirmToken implements Serializable, Cloneable {

    private static final long serialVersionUID = 802986536975736288L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "token",
            nullable = false,
            unique = true
    )
    private String token;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(
            name = "create_date",
            nullable = false
    )
    private Date createDate;

    @Column(
            name = "user_url",
            nullable = false
    )
    private String userUrl;

    @OneToOne(
            targetEntity = User.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    public ConfirmToken() {
    }

    public ConfirmToken(String userUrl, User user) {
        this.user = user;
        this.createDate = new Date();
        this.token = UUID.randomUUID().toString();
        this.userUrl = userUrl;
    }

}
