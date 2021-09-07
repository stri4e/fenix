package com.github.products.controllers.impl;

import com.github.products.controllers.IProductsPagesController;
import com.github.products.dto.ProductDto;
import com.github.products.entity.Criteria;
import com.github.products.entity.Product;
import com.github.products.services.ICriteriaService;
import com.github.products.services.IProductPaginationService;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/v1/pages")
@RequiredArgsConstructor
public class ProductsPagesController implements IProductsPagesController {

    private final IProductPaginationService productPaginationService;

    private final ICriteriaService criteriaService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<ProductDto> findProductsByPage(Pageable pageable) {
        Page<Product> products = this.productPaginationService.read(pageable);
        return new PageImpl<>(
                products.stream()
                        .map(TransferObj::fromProduct)
                        .collect(Collectors.toList()),
                pageable, products.getTotalElements()
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<ProductDto> findProductsByPage(String subcategory, Pageable pageable) {
        Page<Product> products = this.productPaginationService
                .readAllBySubcategory(subcategory, pageable);
        return new PageImpl<>(
                products.stream()
                        .map(TransferObj::fromProduct)
                        .collect(Collectors.toList()),
                pageable, products.getTotalElements()
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<ProductDto>
    findProductsByPageAndFilters(String subcategory, List<Long> criteria, Pageable pageable) {
        List<Criteria> crs = this.criteriaService.readAllByIds(criteria);
        Page<Product> products = this.productPaginationService.readByParams(subcategory, crs, pageable);
        return new PageImpl<>(
                products.stream()
                        .map(TransferObj::fromProduct)
                        .collect(Collectors.toList()),
                pageable, products.getTotalElements()
        );
    }

}
