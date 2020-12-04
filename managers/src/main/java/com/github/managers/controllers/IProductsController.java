package com.github.managers.controllers;

import com.github.managers.dto.ProductDto;
import com.github.managers.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "status") String status
    );

    @PutMapping(
            path = "/many/bought/count",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateBoughtCount(@Valid @RequestBody List<Long> payload);

    @PutMapping(
            path = "/single/bought/count/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateBoughtCountMinus(
            @PathVariable(name = "productId") Long productId
    );

}
