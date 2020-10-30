package com.github.products.controllers.impl;

import com.github.products.entity.Subcategory;

import java.util.List;

public class SubcategoryControllerMocks {

    public static Long SUBCATEGORY_ID = 1L;

    public static String SUBCATEGORY_NAME = "IPhone";

    public static String CATEGORY_UPDATE_NAME = "Smart-Phone";

    public static Subcategory subcategoryForEquals() {
        return new Subcategory(
                SUBCATEGORY_ID,
                SUBCATEGORY_NAME
        );
    }

    public static Subcategory subcategoryForSave() {
        return new Subcategory(
                SUBCATEGORY_NAME
        );
    }

    public static List<Subcategory> subcategories = List.of();

}
