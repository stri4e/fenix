package com.github.users.center.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

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
    private UUID userId;

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

    public RefreshSession(UUID userId,
                          String refreshToken,
                          String fingerprint,
                          String ip,
                          Date expireIn) {
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.fingerprint = fingerprint;
        this.ip = ip;
        this.expireIn = expireIn;
    }

    public RefreshSession(Long id,
                          UUID userId,
                          String refreshToken,
                          String fingerprint,
                          String ip,
                          Date expireIn) {
        this.id = id;
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.fingerprint = fingerprint;
        this.ip = ip;
        this.expireIn = expireIn;
    }

    public boolean isExpired() {
        return new Date().after(this.expireIn);
    }

    public long expireIn() {
        return this.expireIn.getTime();
    }

}
