package com.github.products.controllers;

import com.github.products.dto.CategoryDto;
import com.github.products.entity.Category;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICategoryController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    List<CategoryDto> categories();

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    Category createCategory(@RequestBody Category payload);

    @GetMapping(
            path = "/edit/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Category readByName(@PathVariable String name);

    @PutMapping(
            path = "/edit"
    )
    void updateCategory(@RequestBody Category payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    void removeCategory(@PathVariable Long id);

}
