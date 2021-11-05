package com.github.config.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "keys_settings", schema = "public")
public class KeysSettings implements Serializable {

    private static final long serialVersionUID = -6789248845286106329L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(
            targetEntity = Role.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "priority")
    private int priority;

    @Column(name = "access_token_expire_time")
    private int accessTokenExpireTime;

    @Column(name = "refresh_token_expire_time")
    private int refreshTokenExpireTime;

    @Column(name = "access_token_header_name")
    private String accessTokenHeaderName;

    @Column(name = "access_token_cookie_name")
    private String accessTokenCookieName;

    @Column(name = "refresh_token_cookie_name")
    private String refreshTokenCookieName;

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public int getPriority() {
        return priority;
    }

    public int getAccessTokenExpireTime() {
        return accessTokenExpireTime;
    }

    public int getRefreshTokenExpireTime() {
        return refreshTokenExpireTime;
    }

    public String getAccessTokenHeaderName() {
        return accessTokenHeaderName;
    }

    public String getAccessTokenCookieName() {
        return accessTokenCookieName;
    }

    public String getRefreshTokenCookieName() {
        return refreshTokenCookieName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeysSettings that = (KeysSettings) o;
        return priority == that.priority &&
                accessTokenExpireTime == that.accessTokenExpireTime &&
                refreshTokenExpireTime == that.refreshTokenExpireTime &&
                Objects.equals(id, that.id) &&
                Objects.equals(accessTokenHeaderName, that.accessTokenHeaderName) &&
                Objects.equals(accessTokenCookieName, that.accessTokenCookieName) &&
                Objects.equals(refreshTokenCookieName, that.refreshTokenCookieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priority, accessTokenExpireTime, refreshTokenExpireTime, accessTokenHeaderName, accessTokenCookieName, refreshTokenCookieName);
    }
}
