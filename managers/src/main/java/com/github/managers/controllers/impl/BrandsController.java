package com.github.managers.controllers.impl;

import com.github.managers.controllers.IBrandsController;
import com.github.managers.dto.BrandDto;
import com.github.managers.exceptions.BadRequest;
import com.github.managers.exceptions.NotFound;
import com.github.managers.services.IBrandsService;
import com.github.managers.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/brands")
public class BrandsController implements IBrandsController {

    private final IBrandsService brandsService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public BrandDto save(BrandDto payload) {
        return this.brandsService.create(payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public BrandDto findByName(String name) {
        return this.brandsService.readByName(name)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(BrandDto payload) {
        this.brandsService.update(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.brandsService.delete(id);
    }
}
