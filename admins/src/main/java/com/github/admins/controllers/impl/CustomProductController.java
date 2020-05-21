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

    private final ICategoryService categoryService;

    private final IProductService productService;

    @Override
    public ProductDto addProduct(String categoryName, @Valid ProductDto payload) {
        Category category = this.categoryService.readByName(categoryName);
        Product product = toProduct(payload);
        product.setCategory(category);
        return fromProduct(this.productService.create(product));
    }

    @Override
    public ProductDto getById(Long id) {
        return fromProduct(this.productService.readById(id));
    }

    @Override
    public List<ProductDto> getAllUnPublish() {
        var products = this.productService.readAllUnPublish();
        return products.stream()
                .map(TransferObj::fromProduct)
                .collect(Collectors.toList());
    }

    @Override
    public void updateProduct(@Valid ProductDto payload) {
        this.productService.update(toProduct(payload));
    }

    @Override
    public void updateStatusProduct(Long id, ProductStatus status) {
        this.productService.updateStatus(id, status);
    }

}
