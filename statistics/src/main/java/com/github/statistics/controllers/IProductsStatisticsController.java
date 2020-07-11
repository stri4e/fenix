package com.github.statistics.controllers;

import com.github.statistics.dto.ProductsStatisticsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IProductsStatisticsController {

    @PostMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    ProductsStatisticsDto create(@RequestBody Long payload);

    @GetMapping(
            path = "/{statisticId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    ProductsStatisticsDto readById(@PathVariable Long statisticId);

    @GetMapping(
            path = "/current/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    ProductsStatisticsDto readByProductId(@PathVariable Long productId);

    @PutMapping(
            path = "/review/{productId}"
    )
    @ResponseStatus(value = HttpStatus.OK)
    void updateReview(@PathVariable Long productId);

    @PutMapping(
            path = "/bought/{productId}"
    )
    @ResponseStatus(value = HttpStatus.OK)
    void updateBought(@PathVariable Long productId);

}
