package com.github.products.controllers.impl;

import com.github.products.controllers.IProductDetailsController;
import com.github.products.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/details")
public class ProductDetailsController implements IProductDetailsController {

    private final IProductService productService;

}

