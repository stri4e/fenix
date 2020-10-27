package com.github.admins.controllers.impl;

import com.github.admins.controllers.ISubcategoryController;
import com.github.admins.dto.SubcategoryDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.exceptions.NotFound;
import com.github.admins.services.ISubcategoryService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/subcategory")
public class SubcategoryController implements ISubcategoryController {

    private final ISubcategoryService subcategoryService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public SubcategoryDto save(String categoryName, @Valid SubcategoryDto payload) {
        return this.subcategoryService.create(categoryName, payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public SubcategoryDto findByName(String name) {
        return this.subcategoryService.readByName(name)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(@Valid SubcategoryDto payload) {
        this.subcategoryService.update(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.subcategoryService.delete(id);
    }
}
