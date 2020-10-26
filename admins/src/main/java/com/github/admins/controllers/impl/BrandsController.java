package com.github.admins.controllers.impl;

import com.github.admins.controllers.IBrandsController;
import com.github.admins.dto.BrandDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.exceptions.NotFound;
import com.github.admins.payload.EntityStatus;
import com.github.admins.services.IBrandsService;
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
    public BrandDto create(BrandDto payload) {
        return this.brandsService.create(payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    public BrandDto findByName(String name) {
        return this.brandsService.readByName(name)
                .orElseThrow(NotFound::new);
    }

    @Override
    public List<BrandDto> findAllByStatus(EntityStatus status) {
        return this.brandsService.readAllByStatus(status)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(BrandDto payload) {
        this.brandsService.update(payload);
    }

    @Override
    public void remove(Long id) {
        this.brandsService.delete(id);
    }
}
