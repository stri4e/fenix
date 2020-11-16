package com.github.users.center.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static com.github.users.center.utils.UsersUtils.EXPIRATION_TIME;

@Data
@Entity
@NamedQueries(value = {
        @NamedQuery(
                name = "PassResetToken.findAll",
                query = "SELECT pr FROM PassResetToken pr"
        ),
        @NamedQuery(
                name = "PassResetToken.findByToken",
                query = "SELECT pr FROM PassResetToken pr WHERE pr.token =:token"
        ),
        @NamedQuery(
                name = "PassResetToken.findById",
                query = "SELECT pr FROM PassResetToken pr WHERE pr.id =:id"
        )
})
@Table(
        name = "pass_reset_token",
        schema = "public",
        indexes = @Index(columnList = "token", name = "reset_token_idx")
)
public class PassResetToken implements Serializable, Cloneable {

    private static final long serialVersionUID = -952620960187895254L;

    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "token")
    private String token;

    @Temporal(
            value = TemporalType.TIMESTAMP
    )
    @Column(name = "expireTime")
    private Date expireTime;

    @Column(name = "new_pass")
    private String newPass;

    @OneToOne(
            targetEntity = User.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

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

    public PassResetToken() {
    }

    public PassResetToken(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
    }

    public void setExpiryDate(int minute) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minute);
        this.expireTime = now.getTime();
    }

    public boolean isExpired() {
        return new Date().after(this.expireTime);
    }

    public static PassResetToken build() {
        return new PassResetToken();
    }

    public PassResetToken user(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
        return this;
    }

    public PassResetToken newPass(String newPass) {
        this.newPass = newPass;
        return this;
    }

    public PassResetToken expiryDate(int minute) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minute);
        this.expireTime = now.getTime();
        return this;
    }

}
