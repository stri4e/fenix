package com.github.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long id;

    private String name;

    private List<BranchDto> branches;

    private List<String> cities;

    private BigDecimal homePrice;

    private BigDecimal branchPrice;

}
