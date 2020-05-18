package com.github.products.controllers;

import com.github.products.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ICategoryController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> categories();

}
