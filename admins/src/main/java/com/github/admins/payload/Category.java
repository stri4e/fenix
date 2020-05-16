package com.github.admins.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable, Cloneable {

    private static final long serialVersionUID = -3168450095167684631L;

    private Long id;

    private String name;

    public Category(String name) {
        this.name = name;
    }

}
