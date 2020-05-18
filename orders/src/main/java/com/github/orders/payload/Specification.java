package com.github.orders.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specification implements Serializable, Cloneable {

    private static final long serialVersionUID = -8426888996223798372L;

    private Long id;

    private String name;

    private String description;

    public Specification(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void init(String name, String description) {
        if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(description)) {
            this.name = name;
            this.description = description;
        }
    }

}
