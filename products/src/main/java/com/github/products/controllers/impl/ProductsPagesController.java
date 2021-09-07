package com.github.products.controllers.impl;

import com.github.products.controllers.IProductsPagesController;
import com.github.products.dto.ProductDto;
import com.github.products.entity.Product;
import com.github.products.services.ICriteriaService;
import com.github.products.services.IProductPaginationService;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/pages")
@RequiredArgsConstructor
public class ProductsPagesController implements IProductsPagesController {

    private final IProductPaginationService productPaginationService;

    private final ICriteriaService criteriaService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<ProductDto> searchProduct(String searchLine) {
        return this.productPaginationService
                .searchProduct(searchLine)
                .map(TransferObj::fromProduct);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<ProductDto> findProductsByPage(Pageable pageable) {
        return this.productPaginationService.read(pageable)
                .map(TransferObj::fromProduct);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<ProductDto> findProductsByPage(String subcategory, Pageable pageable) {
        return this.productPaginationService
                .readAllBySubcategory(subcategory, pageable)
                .map(TransferObj::fromProduct);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<ProductDto>
    findProductsByPageAndFilters(String subcategory, List<Long> criteria, Pageable pageable) {
        return this.productPaginationService.readByParams(
                subcategory,
                this.criteriaService.readAllByIds(criteria),
                pageable
        ).map(TransferObj::fromProduct);
    }

}
