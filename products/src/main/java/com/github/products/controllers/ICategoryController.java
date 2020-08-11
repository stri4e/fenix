package com.github.products.controllers;

import com.github.products.dto.CategoryDto;
import com.github.products.entity.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICategoryController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<CategoryDto> findAllCategories();

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    Category saveCategory(@RequestBody Category payload);

    @GetMapping(
            path = "/fetch/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Category findByName(@PathVariable String name);

    @PutMapping(
            path = "/edit"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateCategory(@RequestBody Category payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void removeCategory(@PathVariable Long id);

}
