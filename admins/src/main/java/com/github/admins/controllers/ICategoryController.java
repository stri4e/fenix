package com.github.admins.controllers;

import com.github.admins.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface ICategoryController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto payload);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> getByName(@RequestParam String name);

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CategoryDto>> getAllCategory();

    @PutMapping
    ResponseEntity<Void> updateCategory(@Valid @RequestBody CategoryDto payload);

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> removeCategory(@PathVariable Long id);

}
