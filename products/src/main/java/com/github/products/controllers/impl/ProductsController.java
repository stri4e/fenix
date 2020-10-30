package com.github.products.controllers.impl;

import com.github.products.controllers.IProductsController;
import com.github.products.dto.PercentBoughtDto;
import com.github.products.dto.ProductDto;
import com.github.products.entity.*;
import com.github.products.services.*;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static com.github.products.utils.TransferObj.fromProduct;
import static com.github.products.utils.TransferObj.toProduct;

@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class ProductsController implements IProductsController {

    private final IProductService productService;

    private final ICriteriaService criteriaService;

    private final ISubcategoryService subCategoryService;

    private final IBrandService brandService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<ProductDto> findProductsByPage(Pageable pageable) {
        Page<Product> products = this.productService.read(pageable);
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
    public Page<ProductDto> findProductsByPage(String category, Pageable pageable) {
        Page<Product> products = this.productService
                .readAllByCategory(category, pageable);
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
        List<Criteria> crs = this.criteriaService.readAll(criteria);
        Page<Product> products = this.productService.readByParams(subcategory, crs, pageable);
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
    public List<ProductDto> searchProduct(String searchLine) {
        List<Product> products = this.productService
                .searchProduct(searchLine, searchLine);
        return products.stream()
                .map(TransferObj::fromProduct)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public ProductDto save(String subcategoryName, String brandName, @Valid ProductDto payload) {
        Subcategory category = this.subCategoryService.readByName(subcategoryName);
        Brand brand = this.brandService.findByName(brandName);
        Product tmp = toProduct(payload);
        tmp.setSubcategory(category);
        tmp.setBrand(brand);
        Product product = this.productService.create(tmp);
        return fromProduct(product);
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
        return this.productService.readAllUnPublish().stream()
                .map(TransferObj::fromProduct)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateProduct(ProductDto payload) {
        Product product = this.productService.readById(payload.getId());
        product.setName(payload.getName());
        product.setPrice(payload.getPrice());
        product.setQuantity(payload.getQuantity());
        product.setDescription(payload.getDescription());
        product.setPreviewImage(payload.getPreviewImage());
        product.setImages(payload.getImages());
        this.productService.update(product);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateStatusProduct(Long id, EntityStatus status) {
        this.productService.updateStatus(status, id);
    }

    @Override
    public void updatePercentBoughtProduct(List<PercentBoughtDto> payload) {

    }

}
