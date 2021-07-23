package com.github.products.controllers.impl;

import com.github.products.controllers.IBrandsController;
import com.github.products.dto.BrandDto;
import com.github.products.entity.EntityStatus;
import com.github.products.services.IBrandService;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.products.utils.TransferObj.fromBrand;
import static com.github.products.utils.TransferObj.toBrand;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/brands")
public class BrandsController implements IBrandsController {

    private final IBrandService brandService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public BrandDto save(BrandDto payload) {
        return fromBrand(this.brandService.create(toBrand(payload)));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public BrandDto findByName(String name) {
        return fromBrand(this.brandService.readByName(name));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<BrandDto> findAllByStatus(EntityStatus status) {
        return this.brandService.readAllByStatus(status).stream()
                .map(TransferObj::fromBrand)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(BrandDto payload) {
        this.brandService.update(payload.getId(), payload.getName());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.brandService.delete(id);
    }
}
