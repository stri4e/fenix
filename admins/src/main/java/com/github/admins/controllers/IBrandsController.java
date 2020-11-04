package com.github.admins.controllers;

import com.github.admins.dto.BrandDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IBrandsController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    BrandDto save(@RequestBody BrandDto payload);

    @GetMapping(
            path = "/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    BrandDto findByName(@PathVariable(name = "name") String name);

    @PutMapping()
    void update(@RequestBody BrandDto payload);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
