package com.github.admins.controllers.impl;

import com.github.admins.dto.BrandDto;

import java.util.List;

public class BrandsControllerMocks {

    public static BrandDto request() {
        return new BrandDto(null, "Apple");
    }

    public static BrandDto response() {
        return new BrandDto(1L, "Apple");
    }

    public static List<BrandDto> brands = List.of(
            one(), two(), three(), four(), five()
    );

    public static BrandDto one() {
        return new BrandDto(1L, "Apple");
    }

    public static BrandDto two() {
        return new BrandDto(2L, "Xiaomi");
    }

    public static BrandDto three() {
        return new BrandDto(3L, "Nokia");
    }

    public static BrandDto four() {
        return new BrandDto(4L, "Sumsung");
    }

    public static BrandDto five() {
        return new BrandDto(5L, "Toshiba");
    }

}
