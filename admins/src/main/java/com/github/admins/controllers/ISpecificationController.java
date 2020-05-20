package com.github.admins.controllers;

import com.github.admins.dto.SpecificationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ISpecificationController {

    @PostMapping(
            path = "/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    SpecificationDto addSpecification(@PathVariable Long productId,
                     @Valid @RequestBody SpecificationDto payload);

    @GetMapping(path = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    SpecificationDto getById(@PathVariable Long id);

    @PutMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateSpecification(@RequestBody SpecificationDto payload);

}
