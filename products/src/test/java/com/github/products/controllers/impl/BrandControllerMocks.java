package com.github.products.controllers.impl;

import com.github.products.dto.BrandDto;
import com.github.products.entity.Brand;

import java.util.List;

public class BrandControllerMocks {

    public static final Long BRAND_ID = 1L;

    public static final String BRAND_NAME = "Tir";

    public static Brand brandForSave() {
        return new Brand(BRAND_ID, BRAND_NAME);
    }

    public static BrandDto brandForUpdate() {
        return new BrandDto(1L, "Brand1");
    }

    public static BrandDto brandForEquals() {
        return new BrandDto(BRAND_ID, BRAND_NAME);
    }

    public static Brand brandOneForCreate() {
        return new Brand(null, "Brand1");
    }

    public static Brand brandTwoForCreate() {
        return new Brand(null, "Brand2");
    }

    public static Brand brandThreeForCreate() {
        return new Brand(null, "Brand3");
    }

    public static Brand brandFourForCreate() {
        return new Brand(null, "Brand4");
    }

    public static Brand brandFiveForCreate() {
        return new Brand(null, "Brand5");
    }

    public static List<Brand> BRANDS_FOR_CREATE = List.of(
            brandOneForCreate(),
            brandTwoForCreate(),
            brandThreeForCreate(),
            brandFourForCreate(),
            brandFiveForCreate()
    );

    public static BrandDto brandOneForEquals() {
        return new BrandDto(1L, "Brand1");
    }

    public static BrandDto brandTwoForEquals() {
        return new BrandDto(2L, "Brand2");
    }

    public static BrandDto brandThreeForEquals() {
        return new BrandDto(3L, "Brand3");
    }

    public static BrandDto brandFourForEquals() {
        return new BrandDto(4L, "Brand4");
    }

    public static BrandDto brandFiveForEquals() {
        return new BrandDto(5L, "Brand5");
    }

    public static List<BrandDto> BRANDS_FOR_EQUALS = List.of(
            brandOneForEquals(),
            brandTwoForEquals(),
            brandThreeForEquals(),
            brandFourForEquals(),
            brandFiveForEquals()
    );

}
