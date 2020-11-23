package com.github.managers.controllers;

import com.github.managers.dto.BrandDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IBrandsController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    BrandDto save(@Valid @RequestBody BrandDto payload);

    @GetMapping(
            path = "/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    BrandDto findByName(@PathVariable(name = "name") String name);

    @PutMapping()
    void update(@Valid @RequestBody BrandDto payload);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
