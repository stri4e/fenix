package com.github.admins.controllers.impl;

import com.github.admins.controllers.ISpecificationController;
import com.github.admins.dto.SpecificationDto;
import com.github.admins.exceptions.NotFound;
import com.github.admins.services.ISpecificationService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/specification")
@RequiredArgsConstructor
public class SpecificationController implements ISpecificationController {

    private final ISpecificationService specificationService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public SpecificationDto
    saveSpecification(Long productId, @Valid SpecificationDto payload) {
        return this.specificationService.create(productId, payload)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public SpecificationDto findById(Long id) {
        return this.specificationService.readById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateSpecification(SpecificationDto payload) {
        this.specificationService.update(payload);
    }

}
