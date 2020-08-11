package com.github.products.controllers.impl;

import com.github.products.controllers.ICategoryController;
import com.github.products.dto.CategoryDto;
import com.github.products.entity.Category;
import com.github.products.services.ICategoryService;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<CategoryDto> findAllCategories() {
        return this.categoryService.read().stream()
                .map(TransferObj::fromCategory)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Category saveCategory(Category payload) {
        return this.categoryService.create(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Category findByName(String name) {
        return this.categoryService.readByName(name);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateCategory(Category payload) {
        this.categoryService.update(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void removeCategory(Long id) {
        this.categoryService.remove(id);
    }

}
