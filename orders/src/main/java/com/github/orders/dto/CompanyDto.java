package com.github.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long id;

    private String name;

    private Set<BranchDto> branches;

    private Set<String> cities;

    private BigDecimal homePrice;

    private BigDecimal branchPrice;

}
