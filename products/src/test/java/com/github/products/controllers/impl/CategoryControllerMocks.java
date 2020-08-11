package com.github.products.controllers.impl;

import com.github.products.dto.CategoryDto;
import com.github.products.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryControllerMocks {

    public static Long CATEGORY_ID = 1L;

    public static String CATEGORY_NAME = "Phone";

    public static String CATEGORY_UPDATE_NAME = "Smart-Phone";

    public static final List<CategoryDto> CATEGORIES_DTO = new ArrayList<>() {{
        add(new CategoryDto(1L, "Phone"));
        add(new CategoryDto(2L, "Computer"));
        add(new CategoryDto(3L, "Soft"));
        add(new CategoryDto(4L, "Instruments"));
        add(new CategoryDto(5L, "Sport"));
    }};

    public static final List<Category> CATEGORIES_FOR_SAVE = new ArrayList<>() {{
        add(new Category( "Phone"));
        add(new Category( "Computer"));
        add(new Category( "Soft"));
        add(new Category( "Instruments"));
        add(new Category( "Sport"));
    }};

    public static Category expCategory() {
        return new Category(CATEGORY_ID, CATEGORY_NAME);
    }

    public static Category payloadCategory() {
        return new Category(CATEGORY_NAME);
    }

    public static Category payloadCategoryForUpdate() {
        return new Category(CATEGORY_ID, CATEGORY_UPDATE_NAME);
    }

}
