package com.github.admins.controllers.impl;

import com.github.admins.controllers.ISpecificationController;
import com.github.admins.dto.SpecificationDto;
import com.github.admins.exceptions.NotFound;
import com.github.admins.payload.Product;
import com.github.admins.payload.Specification;
import com.github.admins.services.IProductService;
import com.github.admins.services.ISpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.github.admins.utils.TransferObj.fromSpecification;
import static com.github.admins.utils.TransferObj.toSpecification;

@RestController
@RequestMapping(path = "/v1/specification")
@RequiredArgsConstructor
public class SpecificationController implements ISpecificationController {

    private final IProductService productService;

    private final ISpecificationService specificationService;

    @Override
    public SpecificationDto
    addSpecification(Long productId, @Valid SpecificationDto payload) {
        Specification ts = toSpecification(payload);
        Product product = this.productService.readById(productId)
                .orElseThrow(NotFound::new);;
        Specification spec = this.specificationService.create(ts)
                .orElseThrow(NotFound::new);;
        product.addSpecification(spec);
        this.productService.update(product);
        return fromSpecification(spec);
    }

    @Override
    public SpecificationDto getById(Long id) {
        return fromSpecification(this.specificationService.readById(id)
                .orElseThrow(NotFound::new));
    }

    @Override
    public void updateSpecification(SpecificationDto payload) {
        this.specificationService.update(toSpecification(payload));
    }

}
