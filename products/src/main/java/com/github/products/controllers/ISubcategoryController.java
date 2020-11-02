package com.github.products.controllers;

import com.github.products.dto.SubcategoryDto;
import com.github.products.entity.EntityStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ISubcategoryController {

    @PostMapping(
            path = "/edit/{categoryName}",
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ResponseStatus(code = HttpStatus.CREATED)
    SubcategoryDto save(
            @PathVariable(name = "categoryName") String categoryName,
            @Valid @RequestBody SubcategoryDto payload
    );

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Object findByParams(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "status", required = false) EntityStatus status
    );

    @PutMapping(
            path = "/edit"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@Valid @RequestBody SubcategoryDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable Long id);

}
