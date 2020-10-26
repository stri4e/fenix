package com.github.admins.controllers.impl;

import com.github.admins.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryControllerMocks {

    public static Long CATEGORY_DTO_ID = 1L;

    public static String CATEGORY_DTO_NAME = "Phone";

    public static final List<CategoryDto> CATEGORIES_DTO = new ArrayList<>() {{
        add(new CategoryDto(1L, "Phone"));
        add(new CategoryDto(2L, "Computer"));
        add(new CategoryDto(3L, "Soft"));
        add(new CategoryDto(4L, "Instruments"));
        add(new CategoryDto(5L, "Sport"));
    }};

    public static final List<Category> CATEGORIES = new ArrayList<>() {{
        add(new Category(1L, "Phone"));
        add(new Category(2L, "Computer"));
        add(new Category(3L, "Soft"));
        add(new Category(4L, "Instruments"));
        add(new Category(5L, "Sport"));
    }};

    public static CategoryDto categoryDto() {
        return new CategoryDto(CATEGORY_DTO_ID, CATEGORY_DTO_NAME);
    }

    public static CategoryDto categoryDtoWithoutId() {
        return new CategoryDto(CATEGORY_DTO_NAME);
    }

    public static CategoryDto categoryDtoEmpty() {
        return new CategoryDto();
    }

    public static Category payload() {
        return new Category(CATEGORY_DTO_ID, CATEGORY_DTO_NAME);
    }

}
