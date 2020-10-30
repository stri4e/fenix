package com.github.products.controllers;

import com.github.products.dto.FilterDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IFiltersController {

    @PostMapping(
            path = "/edit/{subcategoryName}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    FilterDto save(
            @PathVariable(name = "subcategoryName") String subcategoryName,
            @Valid @RequestBody FilterDto payload
    );

    @GetMapping(
            path = "/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    FilterDto findById(@PathVariable(name = "id") Long id);

    @PutMapping(
            path = "/edit"
    )
    void update(@Valid @RequestBody FilterDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    void remove(@PathVariable(name = "id") Long id);

}
