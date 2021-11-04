package com.github.config.server.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "instances")
public class Instance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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



}
