package com.github.products.controllers.impl;

import com.github.products.controllers.ISpecificationController;
import com.github.products.dto.SpecificationDto;
import com.github.products.entity.EntityStatus;
import com.github.products.services.ISpecificationService;
import com.github.products.services.ISubcategoryService;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.products.utils.TransferObj.fromSpecification;
import static com.github.products.utils.TransferObj.toSpecification;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/specification")
public class SpecificationController implements ISpecificationController {

    private final ISubcategoryService subcategoryService;

    private final ISpecificationService specificationService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public SpecificationDto save(Long specificationId, SpecificationDto payload) {
        return fromSpecification(
                this.specificationService.create(
                        toSpecification(payload)
                                .addSubcategory(this.subcategoryService.getById(specificationId))
                )
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public SpecificationDto findById(Long id) {
        return fromSpecification(this.specificationService.readById(id));
    }

    @Override
    public List<SpecificationDto> findByAll(Long subcategoryId, EntityStatus status) {
        return this.specificationService.readAllBySubcategoryIdAndStatus(subcategoryId, status).stream()
                .map(TransferObj::fromSpecification).collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(SpecificationDto payload) {
        this.specificationService.updateSpec(toSpecification(payload));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.specificationService.delete(id);
    }

}
