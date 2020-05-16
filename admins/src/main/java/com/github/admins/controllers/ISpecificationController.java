package com.github.admins.controllers;

import com.github.admins.dto.SpecificationDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ISpecificationController {

    @PostMapping(
            path = "/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SpecificationDto>
    addSpecification(@PathVariable Long productId,
                     @Valid @RequestBody SpecificationDto payload);

    @GetMapping(path = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SpecificationDto> getById(@PathVariable Long id);

    @PutMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> updateSpecification(
            @RequestBody SpecificationDto payload);

}
