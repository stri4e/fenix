package com.github.products.controllers.impl;

import com.github.products.controllers.ICategoryController;
import com.github.products.dto.CategoryDto;
import com.github.products.entity.Category;
import com.github.products.services.ICategoryService;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/categories")
public class CategoryController implements ICategoryController {

    private final ICategoryService categoryService;

    @Override
    @Logging(isTime = true, isReturn = false)
    public List<CategoryDto> categories() {
        return this.categoryService.find().stream()
                .map(TransferObj::transferCategory)
                .collect(Collectors.toList());
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public Category createCategory(Category payload) {
        return null;
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public Category readByName(String name) {
        return null;
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void updateCategory(Category payload) {

    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void removeCategory(Long id) {

    }

}
