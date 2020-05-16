package com.github.users.center.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "refresh_session",
        schema = "public"
)
public class RefreshSession implements Serializable, Cloneable {

    private static final long serialVersionUID = -8022389211207894683L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "user_id",
            nullable = false
    )
    private Long userId;

    @Column(
            name = "refresh_token",
            nullable = false,
            columnDefinition = "text",
            length = 5024
    )
    private String refreshToken;

    @Column(
            name = "fingerprint",
            nullable = false
    )
    private String fingerprint;

    @Column(
            name = "ip",
            nullable = false
    )
    private String ip;

    @Temporal(
            value = TemporalType.TIMESTAMP
    )
    @Column(name = "expire_in")
    private Date expireIn;

    @Temporal(
            value = TemporalType.TIMESTAMP
    )
    @Column(name = "expire_at")
    private Date createdAt;

    public RefreshSession(Long userId,
                          String refreshToken,
                          String fingerprint,
                          String ip,
                          Date expireIn,
                          Date createdAt) {
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.fingerprint = fingerprint;
        this.ip = ip;
        this.expireIn = expireIn;
        this.createdAt = createdAt;
    }

    public boolean isExpired() {
        return new Date().after(this.expireIn);
    }

}
