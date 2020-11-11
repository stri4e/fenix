package com.github.deliveries.utils;

import com.github.deliveries.dto.CompanyDto;
import com.github.deliveries.entity.Company;

public class TransferObj {

    public static Company toCompany(CompanyDto data) {
        return new Company(
                data.getId(),
                data.getName()
        );
    }

    public static CompanyDto fromCompany(Company data) {
        return new CompanyDto(
                data.getId(),
                data.getName()
        );
    }

}
