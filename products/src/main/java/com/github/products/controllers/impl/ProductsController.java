package com.github.products.controllers.impl;

import com.github.products.controllers.IProductsController;
import com.github.products.dto.ProductDto;
import com.github.products.dto.SpecificationSectionDto;
import com.github.products.entity.*;
import com.github.products.exceptions.ParametersBadRequest;
import com.github.products.services.*;
import com.github.products.utils.Logging;
import com.github.products.utils.ProductBoughtCount;
import com.github.products.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.github.products.utils.TransferObj.fromProduct;
import static com.github.products.utils.TransferObj.toProduct;

@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class ProductsController implements IProductsController {

    private final IBrandService brandService;

    private final IStocksService stocksService;

    private final IProductService productService;

    private final ISubcategoryService subCategoryService;

    private final ISpecSectionService specSectionService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public ProductDto save(@Valid ProductDto payload) {
        Subcategory subcategory = this.subCategoryService.readById(payload.getSubcategoryId());
        Brand brand = this.brandService.readByName(payload.getBrandName());
        List<SpecificationSection> sections = this.specSectionService.readAllByIds(
                payload.getSpecifications().stream()
                        .map(SpecificationSectionDto::getId)
                        .collect(Collectors.toList())
        );
        Map<Long, Integer> quantities = payload.getStocksQuantity();
        Map<Stock, Integer> stocksQuantity = this.stocksService.readAll(quantities.keySet()).stream()
                .collect(Collectors.toMap(Function.identity(), v -> quantities.get(v.getId())));
        return fromProduct(this.productService.create(
                toProduct(payload)
                        .addSubcategory(subcategory)
                        .brand(brand)
                        .addSpecSections(sections)
                        .addStocksQuantity(stocksQuantity)
        ));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Object findByParams(Long id, List<Long> ids) {
        if (Objects.nonNull(id)) {
            return TransferObj.fromProduct(this.productService.readById(id));
        }
        if (Objects.nonNull(ids)) {
            return this.productService.readAllByIds(ids).stream()
                    .map(TransferObj::fromProduct)
                    .collect(Collectors.toList());
        }
        return this.productService.readAllByStatusOff().stream()
                .map(TransferObj::fromProduct)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateProduct(ProductDto payload) {
        this.productService.updateProduct(toProduct(payload));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateStatusProduct(Long id, EntityStatus status) {
        this.productService.updateStatus(status, id);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateBoughtCount(ProductBoughtCount bought, List<Long> payload) {
        switch (bought) {
            case plus:
                payload.forEach(this.productService::updateBoughtCountPlus);
                break;
            case minus:
                payload.forEach(this.productService::updateBoughtCountMinus);
                break;
            default:
                throw new ParametersBadRequest();
        }
    }

}
