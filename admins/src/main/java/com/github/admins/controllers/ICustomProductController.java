package com.github.admins.controllers;

import com.github.admins.dto.ProductDto;
import com.github.admins.payload.ProductStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface ICustomProductController {

    @PostMapping(
            path = "/{category}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDto>
    addProduct(@PathVariable String category,
               @Valid @RequestBody ProductDto payload
    );

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDto> getById(@PathVariable Long id);

    @GetMapping(
            path = "/un-publish",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductDto>> getAllUnPublish();

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> updateProduct(
            @Valid @RequestBody ProductDto payload
    );

    @DeleteMapping(
            path = "/{id}/{status}")
    ResponseEntity<Void> updateStatusProduct(
            @PathVariable Long id,
            @PathVariable ProductStatus status
    );

}
