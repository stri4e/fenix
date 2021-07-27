package com.github.products.controllers.impl;

import com.github.products.dto.CriteriaDto;
import com.github.products.dto.FilterDto;
import com.github.products.dto.SubcategoryDto;
import com.github.products.entity.Category;
import com.github.products.entity.Subcategory;

import java.util.ArrayList;
import java.util.List;

public class SubcategoryControllerMocks {

    public static String CATEGORY_NAME = "Phone";

    public static String SUBCATEGORY_UPDATE_NAME = "Smart-Phone";

    public static Long SUBCATEGORY_ID = 1L;

    public static String SUBCATEGORY_NAME = "IPhone";

    public static SubcategoryDto subcategoryForUpdate() {
        return new SubcategoryDto(
                SUBCATEGORY_ID,
                SUBCATEGORY_UPDATE_NAME
        );
    }

    public static SubcategoryDto subcategoryEquals() {
        return new SubcategoryDto(
                SUBCATEGORY_ID,
                SUBCATEGORY_NAME,
                new ArrayList<>()
        );
    }

    public static Subcategory subcategoryForSave() {
        return new Subcategory(
                SUBCATEGORY_NAME
        );
    }

    public static SubcategoryDto subcategory() {
        return new SubcategoryDto(
                SUBCATEGORY_NAME,
                List.of(
                        new FilterDto(
                                "custom-filter-1",
                                List.of(
                                     new CriteriaDto("50$")
                                )
                        )
                )
        );
    }

    public static Category categoryForSave() {
        return new Category(CATEGORY_NAME);
    }

    public static Subcategory subcategoryOneForSave() {
        return new Subcategory(
                "one"
        );
    }

    public static Subcategory subcategoryTwoForSave() {
        return new Subcategory(
                "two"
        );
    }

    public static Subcategory subcategoryThreeForSave() {
        return new Subcategory(
                "three"
        );
    }

    public static Subcategory subcategoryFourForSave() {
        return new Subcategory(
                "four"
        );
    }

    public static Subcategory subcategoryFiveForSave() {
        return new Subcategory(
                "five"
        );
    }

    public static List<Subcategory> subcategories = List.of(
            subcategoryOneForSave(),
            subcategoryTwoForSave(),
            subcategoryThreeForSave(),
            subcategoryFourForSave(),
            subcategoryFiveForSave()
    );

    public static SubcategoryDto subcategoryOneForEquals() {
        return new SubcategoryDto(
                1L, "one", new ArrayList<>()
        );
    }

    public static SubcategoryDto subcategoryTwoForEquals() {
        return new SubcategoryDto(
                2L,"two", new ArrayList<>()
        );
    }

    public static SubcategoryDto subcategoryThreeForEquals() {
        return new SubcategoryDto(
                3L,"three", new ArrayList<>()
        );
    }

    public static SubcategoryDto subcategoryFourForEquals() {
        return new SubcategoryDto(
                4L,"four", new ArrayList<>()
        );
    }

    public static SubcategoryDto subcategoryFiveForEquals() {
        return new SubcategoryDto(
                5L,"five", new ArrayList<>()
        );
    }

    public static List<SubcategoryDto> subcategoriesEquals = List.of(
            subcategoryOneForEquals(),
            subcategoryTwoForEquals(),
            subcategoryThreeForEquals(),
            subcategoryFourForEquals(),
            subcategoryFiveForEquals()
    );

}
