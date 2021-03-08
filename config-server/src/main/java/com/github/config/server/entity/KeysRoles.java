package com.github.config.server.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "keys_roles")
public class KeysRoles implements Serializable {

    private static final long serialVersionUID = 345657624440161780L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "role",
            unique = true
    )
    private String role;

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

    public KeysRoles() {
    }

    public KeysRoles(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeysRoles keysRoles = (KeysRoles) o;
        return Objects.equals(id, keysRoles.id) &&
                Objects.equals(role, keysRoles.role) &&
                status == keysRoles.status &&
                Objects.equals(createAt, keysRoles.createAt) &&
                Objects.equals(updateAt, keysRoles.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, status, createAt, updateAt);
    }

    @Override
    public String toString() {
        return "KeysRoles{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", status=" + status +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
