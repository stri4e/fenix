package com.github.config.server.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "keystore")
public class Keystore implements Serializable {

    private static final long serialVersionUID = 9040542676331921779L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile")
    private String profile;

    @Column(
            name = "keysRole",
            columnDefinition = "TEXT"
    )
    private String keysRole;

    @Column(
            name = "keystore",
            columnDefinition = "TEXT"
    )
    private String keystore;

    @Column(name = "status")
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
            nullable = false
    )
    private LocalDateTime updateAt;

    public Keystore() {
    }

    public Keystore(String profile, String keysRole, String keystore) {
        this.keysRole = keysRole;
        this.keystore = keystore;
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public String getKeysRole() {
        return keysRole;
    }

    public String getKeystore() {
        return keystore;
    }

    public EntityStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public String getProfile() {
        return profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keystore keystore1 = (Keystore) o;
        return Objects.equals(id, keystore1.id) &&
                Objects.equals(profile, keystore1.profile) &&
                Objects.equals(keysRole, keystore1.keysRole) &&
                Objects.equals(keystore, keystore1.keystore) &&
                status == keystore1.status &&
                Objects.equals(createAt, keystore1.createAt) &&
                Objects.equals(updateAt, keystore1.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profile, keysRole, keystore, status, createAt, updateAt);
    }

    @Override
    public String toString() {
        return "Keystore{" +
                "id=" + id +
                ", profile='" + profile + '\'' +
                ", keysRole='" + keysRole + '\'' +
                ", keystore='" + keystore + '\'' +
                ", status=" + status +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
