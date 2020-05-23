package com.github.admins.controllers.impl;

import com.github.admins.controllers.ICategoryController;
import com.github.admins.dto.CategoryDto;
import com.github.admins.exceptions.NotFound;
import com.github.admins.payload.Category;
import com.github.admins.services.ICategoryService;
import com.github.admins.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.github.admins.utils.TransferObj.fromCategory;
import static com.github.admins.utils.TransferObj.toCategory;

@RestController
@RequestMapping(path = "/v1/category")
@RequiredArgsConstructor
public class CategoryController implements ICategoryController {

    private final ICategoryService categoryService;

    @Override
    public CategoryDto create(@Valid CategoryDto payload) {
        var tc = toCategory(payload);
        return fromCategory(this.categoryService.create(tc)
                .orElseThrow(NotFound::new));
    }

    @Override
    public Object getCategory(String name) {
        if (!StringUtils.hasText(name)) {
            var categories = this.categoryService.readAll().orElseThrow(NotFound::new);
            return categories.stream()
                    .map(TransferObj::fromCategory)
                    .collect(Collectors.toList());
        }
        var category = this.categoryService.readByName(name)
                .orElseThrow(NotFound::new);
        return fromCategory(category);
    }

    @Override
    public void updateCategory(@Valid CategoryDto payload) {
        this.categoryService.update(toCategory(payload));
    }

    @Override
    public void removeCategory(Long id) {
        this.categoryService.remove(id);
    }
}
