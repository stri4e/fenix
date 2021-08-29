package com.github.products.controllers;

import com.github.products.dto.SpecificationDto;
import com.github.products.entity.EntityStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface ISpecificationController {

    @PostMapping(
            path = "/edit/{specificationId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    SpecificationDto save(
            @PathVariable(name = "specificationId") Long specificationId,
            @Valid @RequestBody SpecificationDto payload
    );

    @GetMapping(
            path = "/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    SpecificationDto findById(@PathVariable Long id);

    @GetMapping(
            path = "/fetch/all/{subcategoryId}/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<SpecificationDto> findByAll(
            @PathVariable(name = "subcategoryId") Long subcategoryId,
            @PathVariable(name = "status")EntityStatus status
    );

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
