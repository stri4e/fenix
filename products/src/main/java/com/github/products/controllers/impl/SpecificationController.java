package com.github.products.controllers.impl;

import com.github.products.controllers.ISpecificationController;
import com.github.products.entity.Specification;
import com.github.products.services.ISpecificationService;
import com.github.products.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/specification")
public class SpecificationController implements ISpecificationController {

    private final ISpecificationService specificationService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Specification save(Specification payload) {
        return this.specificationService.create(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Specification findById(Long id) {
        return this.specificationService.readById(id);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(Specification payload) {
        this.specificationService.update(payload);
    }

}
