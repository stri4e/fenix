package com.github.deliveries.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meest_session", schema = "public")
public class MeestSession implements Serializable, Cloneable {

    private static final long serialVersionUID = -398803201526474943L;

    @Id
    @Column(
            name = "ID"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "token",
            nullable = false
    )
    private String token;

    @Column(
            name = "refresh_token",
            nullable = false
    )
    private String refreshToken;

    @Temporal(
            value = TemporalType.TIMESTAMP
    )
    @Column(
            name = "expire_in",
            nullable = false
    )
    private Date expireIn;

    @Column(
            name = "status",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

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

    public MeestSession(String token, String refreshToken, long time) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.expireIn = new Date(time);
    }

    public boolean hashTokens() {
        return StringUtils.hasText(this.token) && StringUtils.hasText(this.refreshToken);
    }

    public boolean isExpired() {
        return new Date().after(this.expireIn);
    }

}
