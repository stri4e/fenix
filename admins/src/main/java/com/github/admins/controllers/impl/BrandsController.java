package com.github.admins.controllers.impl;

import com.github.admins.controllers.IBrandsController;
import com.github.admins.dto.BrandDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.exceptions.NotFound;
import com.github.admins.payload.EntityStatus;
import com.github.admins.services.IBrandsService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/brands")
public class BrandsController implements IBrandsController {

    private final IBrandsService brandsService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public BrandDto create(BrandDto payload) {
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
    public List<BrandDto> findAllByStatus(EntityStatus status) {
        return this.brandsService.readAllByStatus(status)
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
