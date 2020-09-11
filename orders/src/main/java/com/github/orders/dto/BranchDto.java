package com.github.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {

    private Long id;

    private Integer number;

    private String address;

    private String phone;

}
