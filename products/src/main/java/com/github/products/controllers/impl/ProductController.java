package com.github.products.controllers.impl;

import com.github.products.controllers.IProductController;
import com.github.products.dto.ProductDto;
import com.github.products.entity.Product;
import com.github.products.entity.ProductStatus;
import com.github.products.services.IProductService;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class ProductController implements IProductController {

    private final IProductService productService;

    @Override
    @Logging(isTime = true, isReturn = false)
    public Page<ProductDto> getProduct(Pageable pageable) {
        Page<Product> products = this.productService.find(pageable);
        long total = products.getTotalElements();
        return new PageImpl<>(
                products.stream()
                        .map(TransferObj::transferProduct)
                        .collect(Collectors.toList()), pageable, total
        );
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public Page<ProductDto> getProduct(String category, Pageable pageable) {
        Page<Product> products = this.productService
                .findAllByCategory(category, pageable);
        long total = products.getTotalElements();
        return new PageImpl<>(
                products.stream()
                        .map(TransferObj::transferProduct)
                        .collect(Collectors.toList()), pageable, total
        );
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public Product createProduct(@Valid Product payload) {
        return null;
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public ResponseEntity<?> readByParams(Long id, List<Long> ids) {
        return null;
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void update(Product p) {

    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void updateStatus(Long id, ProductStatus status) {

    }

}
