package com.github.config.server.models;

import java.util.List;
import java.util.Objects;

public class RolePermission {

    private long id;

    private String role;

    private List<String> permission;

    public RolePermission() {
    }

    public RolePermission(long id, String role, List<String> permission) {
        this.id = id;
        this.role = role;
        this.permission = permission;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public List<String> getPermission() {
        return permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RolePermission)) return false;
        RolePermission that = (RolePermission) o;
        return id == that.id && Objects.equals(role, that.role) && Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, permission);
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", permission=" + permission +
                '}';
    }
}
