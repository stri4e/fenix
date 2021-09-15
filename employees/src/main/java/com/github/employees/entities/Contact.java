package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact implements Serializable {

    private static final long serialVersionUID = -5630927194373190406L;

    private Long id;

    private String phone;

    private String extraPhone;

    public Contact(Long id, String phone) {
        this.id = id;
        this.phone = phone;
    }

}
