package com.github.config.server.dto;

import java.util.Objects;

public class KeysRolesDto {

    private Long id;

    private String role;

    public KeysRolesDto() {
    }

    public KeysRolesDto(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeysRolesDto that = (KeysRolesDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return "KeysRolesDto{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
