package com.github.products.controllers.impl;

import com.github.products.controllers.ISpecificationController;
import com.github.products.entity.Specification;
import com.github.products.services.ISpecificationService;
import com.github.products.utils.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/specification")
public class SpecificationController implements ISpecificationController {

    private final ISpecificationService specificationService;

    @Override
    @Logging(isTime = true, isReturn = false)
    public Specification create(Specification payload) {
        return this.specificationService.create(payload);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public Specification readById(Long id) {
        return this.specificationService.readById(id);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void update(Specification payload) {
        this.specificationService.update(payload);
    }

}
