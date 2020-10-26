package com.github.admins.controllers.impl;

import com.github.admins.controllers.IProductsController;
import com.github.admins.dto.ProductDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.exceptions.NotFound;
import com.github.admins.payload.EntityStatus;
import com.github.admins.services.IProductService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/product")
@RequiredArgsConstructor
public class ProductsController implements IProductsController {

    private final IProductService productService;

    @Override
    public ProductDto save(String subcategoryName, String brandName, @Valid ProductDto payload) {
        return this.productService.create(subcategoryName, brandName, payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public ProductDto findById(Long id) {
        return this.productService.readById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<ProductDto> findAllUnPublish() {
        return this.productService.readAllUnPublish()
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateProduct(@Valid ProductDto payload) {
        this.productService.update(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void changeStatusProduct(Long id, EntityStatus status) {
        this.productService.updateStatus(id, status);
    }

}
