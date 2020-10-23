package com.github.products.controllers;

import com.github.products.dto.SubCategoryDto;
import com.github.products.entity.EntityStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface ISubCategoryController {

    @PostMapping(
            path = "/edit/{categoryName}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    SubCategoryDto save(
            @PathVariable(name = "categoryName") String categoryName,
            @Valid @RequestBody SubCategoryDto payload
    );

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Object findByParams(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "status", required = false) EntityStatus status
    );

    @GetMapping(
            path = "/{categoryName}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<SubCategoryDto> findAllByCategoryName(
            @PathVariable(name = "categoryName") String categoryName
    );

    @PutMapping(
            path = "/edit"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@Valid @RequestBody SubCategoryDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void remove(@PathVariable Long id);

}
