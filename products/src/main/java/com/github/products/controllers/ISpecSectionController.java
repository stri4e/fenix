package com.github.products.controllers;

import com.github.products.dto.SpecSectionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ISpecSectionController {

    @PostMapping(
            path = "/edit"
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    SpecSectionDto save(@Valid @RequestBody SpecSectionDto payload);

    @PutMapping(path = "/edit/{specSectionId}/{title}")
    void updateTitle(
            @PathVariable(name = "specSectionId") Long specSectionId,
            @PathVariable(name = "title") String title
    );

    @DeleteMapping(path = "/edit/{specSectionId}")
    void deleteById(@PathVariable(name = "specSectionId") Long specSectionId);

}
