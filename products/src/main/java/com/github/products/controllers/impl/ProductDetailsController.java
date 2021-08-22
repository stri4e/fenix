package com.github.products.controllers.impl;

import com.github.products.controllers.IProductDetailsController;
import com.github.products.dto.CriteriaDto;
import com.github.products.dto.ProductDetailsDto;
import com.github.products.dto.SpecSectionDto;
import com.github.products.entity.*;
import com.github.products.services.*;
import com.github.products.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/details")
public class ProductDetailsController implements IProductDetailsController {

    private final IProductService productService;

    private final IBrandService brandService;

    private final ISubcategoryService subcategoryService;

    private final ICriteriaService criteriaService;

    private final ISpecSectionService specSectionService;

    private final IStocksService stocksService;

    private final IProductStockLinkService productStockLinkService;
    
    @Override
    public ProductDetailsDto create(@Valid ProductDetailsDto payload) {
        Brand brand = this.brandService.readByName(payload.getBrandName());
        Subcategory subcategory = this.subcategoryService.readById(payload.getSubcategoryId());
        List<Criteria> criteria = this.criteriaService.readAllByIds(
                payload.getCriteria().stream()
                        .map(CriteriaDto::getId)
                        .collect(Collectors.toList())
        );
        List<SpecSection> sections = this.specSectionService.readAllByIds(
                payload.getSpecifications().stream()
                        .map(SpecSectionDto::getId)
                        .collect(Collectors.toList())
        );
        Map<Long, Stock> stocks = this.stocksService.readAll(
                payload.getLinks().stream()
                        .map(link -> link.getStock().getId())
                        .collect(Collectors.toSet())
        ).stream().collect(Collectors.toMap(Stock::getId, Function.identity()));
        Product product = this.productService.create(
                TransferObj.toProduct(payload)
                .addSpecSection(sections)
                .addBrand(brand)
                .addSubcategory(subcategory)
                .addAllCriteria(criteria)
        );
        this.productStockLinkService.createAll(
                payload.getLinks().stream()
                .map(l -> new ProductStockLink(
                        product,
                        stocks.get(l.getStock().getId()),
                        l.getQuantity())
                ).collect(Collectors.toList())
        );
        return TransferObj.ofProduct(product);
    }

}

