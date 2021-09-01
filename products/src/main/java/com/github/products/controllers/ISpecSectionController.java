package com.github.products.controllers;

import com.github.products.dto.SpecificationSectionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ISpecSectionController {

    @PostMapping(
            path = "/edit/{productId}"
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    SpecificationSectionDto save(
            @PathVariable(value = "productId") Long productId,
            @Valid @RequestBody SpecificationSectionDto payload
    );

    @PutMapping(
            path = "/edit/specification/{sectionId}/{specificationId}"
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    SpecificationSectionDto save(
            @PathVariable(value = "sectionId") Long sectionId,
            @PathVariable(value = "specificationId") Long specificationId
    );

    @PutMapping(path = "/edit/{specSectionId}/{title}")
    void updateTitle(
            @PathVariable(name = "specSectionId") Long specSectionId,
            @PathVariable(name = "title") String title
    );

    @DeleteMapping(path = "/edit/specification/{sectionId}/{specificationId}")
    void deleteSpec(
            @PathVariable(value = "sectionId") Long sectionId,
            @PathVariable(value = "specificationId") Long specificationId
    );

    @DeleteMapping(path = "/edit/{specSectionId}")
    void deleteById(@PathVariable(name = "specSectionId") Long specSectionId);

}
