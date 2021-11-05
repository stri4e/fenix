package com.github.config.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(schema = "public", name = "instances")
public class Instance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;

    @ManyToMany(
            mappedBy = "instances",
            fetch = FetchType.EAGER,
            targetEntity = Role.class,
            cascade = CascadeType.ALL
    )
    private Set<Role> roles;

    public Instance() {
    }

    public Instance(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

}
