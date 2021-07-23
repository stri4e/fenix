package com.github.products.services.impl;

import com.github.products.entity.Brand;
import com.github.products.entity.EntityStatus;

import java.util.List;

public class BrandServiceMock {

    public static Brand dataToCreate() {
        return new Brand("brand-test");
    }

    public static Brand entityInDatabase_id_1() {
        return new Brand(1L, "brand-test");
    }

    public static Brand entityInDatabase_id_2() {
        return new Brand(1L, "brand-test");
    }

    public static List<Brand> entitiesInDatabase_statusOn() {
        return List.of(
                new Brand(1L, "brand-test-1"),
                new Brand(2L, "brand-test-2"),
                new Brand(3L, "brand-test-3"),
                new Brand(4L, "brand-test-4"),
                new Brand(5L, "brand-test-5")
        );
    }

    public static List<Brand> entitiesInDatabase_statusOff() {
        return List.of(
                new Brand(1L, "brand-test-1", EntityStatus.off),
                new Brand(2L, "brand-test-2", EntityStatus.off),
                new Brand(3L, "brand-test-3", EntityStatus.off),
                new Brand(4L, "brand-test-4", EntityStatus.off),
                new Brand(5L, "brand-test-5", EntityStatus.off)
        );
    }

}
