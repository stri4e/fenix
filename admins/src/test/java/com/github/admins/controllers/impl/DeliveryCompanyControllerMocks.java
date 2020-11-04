package com.github.admins.controllers.impl;

import com.github.admins.dto.CompanyDto;

public class DeliveryCompanyControllerMocks {

    public static CompanyDto request() {
        return new CompanyDto(
            null, "Burning"
        );
    }

    public static CompanyDto response() {
        return new CompanyDto(
                1L, "Burning"
        );
    }

}
