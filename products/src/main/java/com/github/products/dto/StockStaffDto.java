package com.github.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockStaffDto implements Serializable {

    private static final long serialVersionUID = -7052318227944088224L;

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Set<String> phones;

}
