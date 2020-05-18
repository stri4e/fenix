package com.github.admins.controllers.impl;

import com.github.admins.controllers.ISpecificationController;
import com.github.admins.dto.SpecificationDto;
import com.github.admins.payload.Product;
import com.github.admins.payload.Specification;
import com.github.admins.services.IProductService;
import com.github.admins.services.ISpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.github.admins.utils.TransferObj.fromSpecification;
import static com.github.admins.utils.TransferObj.toSpecification;

@RestController
@RequestMapping(path = "/v1/specification")
@RequiredArgsConstructor
public class SpecificationController implements ISpecificationController {

    private final IProductService ps;

    private final ISpecificationService ss;

    @Override
    public ResponseEntity<SpecificationDto>
    addSpecification(Long productId, @Valid SpecificationDto payload) {
        Specification ts = toSpecification(payload);
        Product product = this.ps.readById(productId);
        Specification spec = this.ss.create(ts);
        product.addSpecification(spec);
        this.ps.update(product);
        return new ResponseEntity<>(
                fromSpecification(spec),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<SpecificationDto> getById(Long id) {
        Specification spec = this.ss.readById(id);
        return new ResponseEntity<>(
                fromSpecification(spec), HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Void>
    updateSpecification(SpecificationDto payload) {
        this.ss.update(toSpecification(payload));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
