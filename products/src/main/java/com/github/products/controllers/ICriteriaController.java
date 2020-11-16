package com.github.products.controllers;

import com.github.products.dto.CriteriaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface ICriteriaController {

    @PostMapping(
            path = "/edit/to/filters/{filterId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    CriteriaDto saveToFilters(
            @PathVariable(name = "filterId") Long filterId,
            @Valid @RequestBody CriteriaDto payload
    );

    @PostMapping(
            path = "/edit/to/products/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    void saveToProducts(
            @PathVariable(name = "productId") Long productId,
            @Valid @RequestBody List<Long> payload
    );

    @GetMapping(
            path = "/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CriteriaDto findById(@PathVariable Long id);

    @PutMapping(
            path = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@Valid @RequestBody CriteriaDto payload);

    @PutMapping(
            path = "/edit/in/products/{productId}/{criteriaId}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateInProducts(
            @PathVariable(name = "productId") Long productId,
            @PathVariable(name = "criteriaId")  Long criteriaId
    );

    @DeleteMapping(
            path = "/edit/in/products/{productId}/{criteriaId}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void removeInProducts(
            @PathVariable(name = "productId") Long productId,
            @PathVariable(name = "criteriaId")  Long criteriaId
    );

    @PutMapping(
            path = "/edit/in/filters/{filterId}/{criteriaId}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateInFilters(
            @PathVariable(name = "filterId") Long filterId,
            @PathVariable(name = "criteriaId")  Long criteriaId
    );

    @DeleteMapping(
            path = "/edit/in/filters/{filterId}/{criteriaId}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void removeInFilters(
            @PathVariable(name = "filterId") Long filterId,
            @PathVariable(name = "criteriaId")  Long criteriaId
    );

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable Long id);

}
