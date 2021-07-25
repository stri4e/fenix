package com.github.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockStaffDto {

    private String firstName;

    private String lastName;

    private String email;

    private List<String> phone;

}
