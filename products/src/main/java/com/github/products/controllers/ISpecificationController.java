package com.github.products.controllers;

import com.github.products.entity.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface ISpecificationController {

    @PostMapping(
            path = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Specification create(Specification payload);

    @GetMapping(
            path = "/info",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Specification readById(Long id);

    @PutMapping(
            path = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    void update(Specification payload);

}
