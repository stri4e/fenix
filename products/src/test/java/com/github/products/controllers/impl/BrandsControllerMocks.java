package com.github.products.controllers.impl;

import com.github.products.dto.BrandDto;
import com.github.products.entity.Brand;

public class BrandsControllerMocks {

    public static Brand entityBefore_create() {
        return new Brand("Nike");
    }

    public static Brand entityAfter_create() {
        return new Brand(1L, "Nike");
    }

    public static BrandDto requestTo_saveBrand() {
        return new BrandDto("Nike");
    }

    public static BrandDto responseFrom_saveBrand() {
        return new BrandDto(1L, "Nike");
    }

}
