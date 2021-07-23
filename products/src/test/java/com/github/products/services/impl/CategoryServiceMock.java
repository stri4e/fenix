package com.github.products.services.impl;

import com.github.products.entity.Category;
import com.github.products.entity.Subcategory;

import java.time.LocalDateTime;
import java.util.List;

public class CategoryServiceMock {

    public static List<Category> entities_inDatabase_statusOn() {
        LocalDateTime time = LocalDateTime.now();
        return List.of(
                new Category(
                        1L,
                        "category-test-1",
                        List.of(),
                        time,
                        time
                ), new Category(
                        2L,
                        "category-test-2",
                        List.of(),
                        time,
                        time
                ), new Category(
                        3L,
                        "category-test-3",
                        List.of(),
                        time,
                        time
                ), new Category(
                        4L,
                        "category-test-4",
                        List.of(),
                        time,
                        time
                ), new Category(
                        5L,
                        "category-test-5",
                        List.of(),
                        time,
                        time
                )
        );
    }

    public static Category entity_toSave() {
        LocalDateTime time = LocalDateTime.now();
        return new Category(
                "category-test-1",
                List.of(),
                time,
                time
        );
    }

    public static Category entity_toUpdate() {
        LocalDateTime time = LocalDateTime.now();
        return new Category(
                1L,
                "category-test-1",
                List.of(),
                time,
                time
        );
    }

    public static Category entity_inDatabase() {
        LocalDateTime time = LocalDateTime.now();
        return new Category(
                1L,
                "category-test-1",
                List.of(),
                time,
                time
        );
    }

}
