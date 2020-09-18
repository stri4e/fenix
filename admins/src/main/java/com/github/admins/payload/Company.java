package com.github.admins.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable, Cloneable {

    private static final long serialVersionUID = -3032898280129319548L;

    private Long id;

    private String name;

    private Set<Branch> branches = new HashSet<>();

    private Set<String> cities =  new HashSet<>();

    private BigDecimal homePrice;

    private BigDecimal branchPrice;

    private CompanyStatus status;

    public Company(
            Long id, String name,
            Set<Branch> branches, Set<String> cities,
            BigDecimal homePrice, BigDecimal branchPrice) {
        this.id = id;
        this.name = name;
        this.branches = branches;
        this.cities = cities;
        this.homePrice = homePrice;
        this.branchPrice = branchPrice;
    }

    public void addBranch(Branch b) {
        if (Objects.nonNull(b)) {
            this.branches.add(b);
        }
    }

}
