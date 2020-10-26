package com.github.admins.controllers;

import com.github.admins.dto.SubcategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ISubcategoryController {

    @PostMapping(
            path = "/{categoryName}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    SubcategoryDto save(
            @PathVariable(name = "categoryName") String categoryName,
            @Valid @RequestBody SubcategoryDto payload
    );

    @GetMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    SubcategoryDto findByName(
            @RequestParam(name = "name", required = false) String name
    );

    @PutMapping(
            path = "/"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@Valid @RequestBody SubcategoryDto payload);

    @DeleteMapping(
            path = "/{id}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void remove(@PathVariable Long id);

}
