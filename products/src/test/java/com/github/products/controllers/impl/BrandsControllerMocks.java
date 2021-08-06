package com.github.products.controllers.impl;

import com.github.products.dto.BrandDto;
import com.github.products.entity.Brand;

import java.util.List;

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

    public static BrandDto requestTo_updateBrand() {
        return new BrandDto(1L, "Nike");
    }

    public static Brand entityIn_database() {
        return new Brand(1L, "Nike");
    }

    public static BrandDto responseFrom_controller() {
        return new BrandDto(1L, "Nike");
    }

    public static List<Brand> entitiesInDatabaseMany_findAllByStatusOn() {
        return List.of(
                new Brand(1L, "Nike"),
                new Brand(2L, "Puma"),
                new Brand(3L, "Adidas"),
                new Brand(4L, "Lucifer"),
                new Brand(5L, "Umbrella")
        );
    }

    public static List<Brand> entitiesInDatabaseTwo_findAllByStatusOn() {
        return List.of(
                new Brand(1L, "Nike"),
                new Brand(2L, "Puma")
        );
    }

    public static List<Brand> entitiesInDatabaseOne_findAllByStatusOn() {
        return List.of(
                new Brand(1L, "Nike")
        );
    }

    public static List<Brand> entitiesInDatabaseMany_findAllByStatusOff() {
        return List.of(
                new Brand(1L, "Nike"),
                new Brand(2L, "Puma"),
                new Brand(3L, "Adidas"),
                new Brand(4L, "Lucifer"),
                new Brand(5L, "Umbrella")
        );
    }

    public static List<Brand> entitiesInDatabaseTwo_findAllByStatusOff() {
        return List.of(
                new Brand(1L, "Nike"),
                new Brand(2L, "Puma")
        );
    }

    public static List<Brand> entitiesInDatabaseOne_findAllByStatusOff() {
        return List.of(
                new Brand(1L, "Nike")
        );
    }

    public static List<BrandDto> responseBrandArrayManyFrom_controller() {
        return List.of(
                new BrandDto(1L, "Nike"),
                new BrandDto(2L, "Puma"),
                new BrandDto(3L, "Adidas"),
                new BrandDto(4L, "Lucifer"),
                new BrandDto(5L, "Umbrella")
        );
    }

    public static List<BrandDto> responseBrandArrayTwoFrom_controller() {
        return List.of(
                new BrandDto(1L, "Nike"),
                new BrandDto(2L, "Puma")
        );
    }

    public static List<BrandDto> responseBrandArrayOneFrom_controller() {
        return List.of(
                new BrandDto(1L, "Nike")
        );
    }

}
