package com.github.products.controllers;

import com.github.products.dto.SpecificationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ISpecificationController {

    @PostMapping(
            path = "/edit/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    SpecificationDto save(
            @PathVariable(name = "productId") Long productId,
            @Valid @RequestBody SpecificationDto payload
    );

    @GetMapping(
            path = "/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    SpecificationDto findById(@PathVariable Long id);

    @PutMapping(
            path = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@Valid @RequestBody SpecificationDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void remove(@PathVariable(name = "id") Long id);

}
