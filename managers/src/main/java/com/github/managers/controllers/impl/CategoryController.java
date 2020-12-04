package com.github.managers.controllers.impl;

import com.github.managers.controllers.ICategoryController;
import com.github.managers.dto.CategoryDto;
import com.github.managers.exceptions.NotFound;
import com.github.managers.services.ICategoryService;
import com.github.managers.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/category")
@RequiredArgsConstructor
public class CategoryController implements ICategoryController {

    private final ICategoryService categoryService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public CategoryDto saveCategory(@Valid CategoryDto payload) {
        return this.categoryService.create(payload)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Object findByName(String name) {
        if (!StringUtils.hasText(name)) {
            return this.categoryService.readAll().orElseThrow(NotFound::new);
        }
        return this.categoryService.readByName(name)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateCategory(@Valid CategoryDto payload) {
        this.categoryService.update(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void removeCategory(Long id) {
        this.categoryService.delete(id);
    }
}
