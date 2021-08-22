package com.github.products.controllers;

import com.github.products.dto.ProductDetailsDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IProductDetailsController {
    @PostMapping(path = "/edit")
    ProductDetailsDto create(@Valid @RequestBody ProductDetailsDto payload);
}
