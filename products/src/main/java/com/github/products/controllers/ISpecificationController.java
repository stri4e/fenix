package com.github.products.controllers;

import com.github.products.entity.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface ISpecificationController {

    @PostMapping(
            path = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    Specification saveSpecification(@RequestBody Specification payload);

    @GetMapping(
            path = "/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Specification findById(@PathVariable Long id);

    @PutMapping(
            path = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateSpecification(@RequestBody Specification payload);

}
