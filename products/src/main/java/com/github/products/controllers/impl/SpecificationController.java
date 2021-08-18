package com.github.products.controllers.impl;

import com.github.products.controllers.ISpecificationController;
import com.github.products.dto.SpecificationDto;
import com.github.products.entity.Product;
import com.github.products.entity.Specification;
import com.github.products.services.IProductService;
import com.github.products.services.ISpecificationService;
import com.github.products.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.products.utils.TransferObj.fromSpecification;
import static com.github.products.utils.TransferObj.toSpecification;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/specification")
public class SpecificationController implements ISpecificationController {

    private final ISpecificationService specificationService;

    private final IProductService productService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public SpecificationDto save(Long productId, SpecificationDto payload) {
        Product product = this.productService.getById(productId);
        Specification tmp = toSpecification(payload);
        Specification specification = this.specificationService.create(tmp);
        //product.addSpecification(specification);
        this.productService.update(product);
        return fromSpecification(specification);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public SpecificationDto findById(Long id) {
        return fromSpecification(this.specificationService.readById(id));
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
