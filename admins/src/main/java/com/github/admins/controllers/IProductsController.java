package com.github.admins.controllers;

import com.github.admins.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IProductsController {

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    ProductDto save(@Valid @RequestBody ProductDto payload);

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    ProductDto findById(@PathVariable Long id);

    @GetMapping(
            path = "/un-publish",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    List<ProductDto> findAllUnPublish();

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    void updateProduct(
            @Valid @RequestBody ProductDto payload
    );

    @DeleteMapping(
            path = "/{id}/{status}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void changeStatusProduct(
            @PathVariable Long id,
            @PathVariable String status
    );

}
