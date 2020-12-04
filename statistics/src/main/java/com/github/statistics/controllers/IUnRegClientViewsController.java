package com.github.statistics.controllers;

import com.github.statistics.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IUnRegClientViewsController {

    @PostMapping(
            path = "/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    void save(
            @RequestAttribute(name = "ip") String ip,
            @PathVariable(name = "productId") Long productId
    );

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProductDto> findByIp(@RequestAttribute(name = "ip") String ip);

}
