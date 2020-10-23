package com.github.products.controllers.impl;

import com.github.products.controllers.IBrandController;
import com.github.products.dto.BrandDto;
import com.github.products.entity.Brand;
import com.github.products.entity.EntityStatus;
import com.github.products.services.IBrandService;
import com.github.products.utils.TransferObj;
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
public class BrandController implements IBrandController {

    private final IBrandService brandService;

    @Override
    public BrandDto save(BrandDto payload) {
        return fromBrand(this.brandService.create(toBrand(payload)));
    }

    @Override
    public BrandDto findByName(String name) {
        return fromBrand(this.brandService.findByName(name));
    }

    @Override
    public List<BrandDto> findAllByStatus(EntityStatus status) {
        return this.brandService.readAllByStatus(status).stream()
                .map(TransferObj::fromBrand)
                .collect(Collectors.toList());
    }

    @Override
    public void update(BrandDto payload) {
        Brand brand = this.brandService.findById(payload.getId());
        brand.setName(payload.getName());
        this.brandService.update(brand);
    }

    @Override
    public void remove(Long id) {
        this.brandService.delete(id);
    }
}
