package com.github.admins.controllers;

import com.github.admins.dto.CategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ICategoryController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    CategoryDto saveCategory(@Valid @RequestBody CategoryDto payload);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    Object findByName(@RequestParam(required = false) String name);

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    void updateCategory(@Valid @RequestBody CategoryDto payload);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void removeCategory(@PathVariable Long id);

}
