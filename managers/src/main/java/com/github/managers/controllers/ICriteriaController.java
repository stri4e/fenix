package com.github.managers.controllers;

import com.github.managers.dto.CriteriaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICriteriaController {

    @PostMapping(
            path = "/to/filters/{filterId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    CriteriaDto saveToFilters(
            @PathVariable(name = "filterId") Long filterId,
            @RequestBody CriteriaDto payload
    );

    @PostMapping(
            path = "/to/products/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    void saveToProducts(
            @PathVariable(name = "productId") Long productId,
            @RequestBody List<Long> payload
    );

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CriteriaDto findById(@PathVariable Long id);

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@RequestBody CriteriaDto payload);

    @PutMapping(
            path = "/in/products/{productId}/{criteriaId}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateInProducts(
            @PathVariable(name = "productId") Long productId,
            @PathVariable(name = "criteriaId")  Long criteriaId
    );

    @DeleteMapping(
            path = "/in/products/{productId}/{criteriaId}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void removeInProducts(
            @PathVariable(name = "productId") Long productId,
            @PathVariable(name = "criteriaId")  Long criteriaId
    );

    @PutMapping(
            path = "/in/filters/{filterId}/{criteriaId}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateInFilters(
            @PathVariable(name = "filterId") Long filterId,
            @PathVariable(name = "criteriaId")  Long criteriaId
    );

    @DeleteMapping(
            path = "/in/filters/{filterId}/{criteriaId}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void removeInFilters(
            @PathVariable(name = "filterId") Long filterId,
            @PathVariable(name = "criteriaId")  Long criteriaId
    );

    @DeleteMapping(
            path = "/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable Long id);

}
