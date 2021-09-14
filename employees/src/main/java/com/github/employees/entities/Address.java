package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = -2190901916194264898L;

    private Long id;

    private String country;

    private String region;

    private String city;

    private String street;

    private String streetNumber;

    private String houseNumber;

    private String flatNumber;

    private String zipCode;

    private EntityStatus status = EntityStatus.on;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

}