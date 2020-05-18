package com.github.admins.controllers.impl;

import com.github.admins.controllers.ICustomProductController;
import com.github.admins.dto.ProductDto;
import com.github.admins.payload.Category;
import com.github.admins.payload.Product;
import com.github.admins.payload.ProductStatus;
import com.github.admins.services.ICategoryService;
import com.github.admins.services.IProductService;
import com.github.admins.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.admins.utils.TransferObj.fromProduct;
import static com.github.admins.utils.TransferObj.toProduct;

@RestController
@RequestMapping(path = "/v1/product")
@RequiredArgsConstructor
public class CustomProductController implements ICustomProductController {

    private final ICategoryService cs;

    private final IProductService ps;

    @Override
    public ResponseEntity<ProductDto>
    addProduct(String categoryName, @Valid ProductDto payload) {
        Category category = this.cs.readByName(categoryName);
        Product product = toProduct(payload);
        product.setCategory(category);
        return new ResponseEntity<>(
                fromProduct(this.ps.create(product)),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<ProductDto> getById(Long id) {
        Product p = this.ps.readById(id);
        return new ResponseEntity<>(fromProduct(p), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductDto>> getAllUnPublish() {
        List<Product> products = this.ps.readAllUnPublish();
        List<ProductDto> result = products.stream()
                .map(TransferObj::fromProduct)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateProduct(@Valid ProductDto payload) {
        Product result = toProduct(payload);
        this.ps.update(result);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void>
    updateStatusProduct(Long id, ProductStatus status) {
        this.ps.updateStatus(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
