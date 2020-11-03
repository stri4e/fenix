package com.github.orders.controllers.impl;

import com.github.orders.dto.CompanyDto;
import com.github.orders.entity.Company;

import java.util.List;

public class DeliveryCompaniesMocks {

    public static CompanyDto requestCompany() {
        return new CompanyDto(
                null, "Company1"
        );
    }

    public static CompanyDto requestCompanyForUpdate() {
        return new CompanyDto(
                1L, "Company2"
        );
    }

    public static CompanyDto companyForEquals() {
        return new CompanyDto(
                1L, "Company1"
        );
    }

    public static CompanyDto companyForEqualsAfterUpdate() {
        return new CompanyDto(
                1L, "Company2"
        );
    }

    public static Company companyForSave() {
        return new Company(
                null, "Company1"
        );
    }

    public static List<Company> COMPANIES_FOR_SAVE = List.of(
            companyOneForSave(),
            companyTwoForSave(),
            companyThreeForSave(),
            companyFourForSave(),
            companyFiveForSave()
    );

    public static Company companyOneForSave() {
        return new Company(
                null, "Company1"
        );
    }

    public static Company companyTwoForSave() {
        return new Company(
                null, "Company2"
        );
    }

    public static Company companyThreeForSave() {
        return new Company(
                null, "Company3"
        );
    }

    public static Company companyFourForSave() {
        return new Company(
                null, "Company4"
        );
    }

    public static Company companyFiveForSave() {
        return new Company(
                null, "Company5"
        );
    }

    public static List<CompanyDto> COMPANIES_FOR_EQUALS = List.of(
            companyOneForEquals(),
            companyTwoForEquals(),
            companyThreeForEquals(),
            companyFourForEquals(),
            companyFiveForEquals()
    );

    public static CompanyDto companyOneForEquals() {
        return new CompanyDto(
                1L, "Company1"
        );
    }

    public static CompanyDto companyTwoForEquals() {
        return new CompanyDto(
                2L, "Company2"
        );
    }

    public static CompanyDto companyThreeForEquals() {
        return new CompanyDto(
                3L, "Company3"
        );
    }

    public static CompanyDto companyFourForEquals() {
        return new CompanyDto(
                4L, "Company4"
        );
    }

    public static CompanyDto companyFiveForEquals() {
        return new CompanyDto(
                5L, "Company5"
        );
    }

}
