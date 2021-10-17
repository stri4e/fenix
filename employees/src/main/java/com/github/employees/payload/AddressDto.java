package com.github.employees.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto implements Serializable {

    private static final long serialVersionUID = -2190901916194264898L;

    private String country;

    private String region;

    private String city;

    private String street;

    private String streetNumber;

    private String houseNumber;

    private String flatNumber;

    private String zipCode;

}
