package com.github.config.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "public")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private List<String> permission;

    @ManyToMany(
            cascade = CascadeType.ALL,
            targetEntity = Instance.class
    )
    @JoinTable(
            name = "instance_roles",
            joinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "instances_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Instance> instances;

    @OneToOne(
            mappedBy = "role",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            targetEntity = KeysSettings.class
    )
    private KeysSettings keysSettings;

    public Role() {
    }

    public Role(String name, List<String> permission) {
        this.name = name;
        this.permission = permission;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getPermission() {
        return permission;
    }

    public Set<Instance> getInstances() {
        return instances;
    }

    public KeysSettings getKeysSettings() {
        return keysSettings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
