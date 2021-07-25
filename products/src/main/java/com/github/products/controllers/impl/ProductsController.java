package com.github.products.controllers.impl;

import com.github.products.controllers.IProductsController;
import com.github.products.dto.ProductDto;
import com.github.products.entity.*;
import com.github.products.services.IBrandService;
import com.github.products.services.IProductService;
import com.github.products.services.IStocksService;
import com.github.products.services.ISubcategoryService;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
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

    private final ISubcategoryService subCategoryService;

    private final IBrandService brandService;

    private final IStocksService stocksService;

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
    public ProductDto save(@Valid ProductDto payload) {
        Map<Long, Integer> quantityGroupByStockId = payload.getQuantityGroupByStockId();
        Subcategory category = this.subCategoryService.readById(payload.getSubcategoryId());
        Brand brand = this.brandService.readByName(payload.getBrandName());
        List<ProductStockLink> links = this.stocksService.readAll(quantityGroupByStockId.keySet()).stream()
                .map(stock -> new ProductStockLink()
                        .addStock(stock)
                        .quantity(quantityGroupByStockId.get(stock.getId())))
                .collect(Collectors.toList());
        Product tmp = toProduct(payload)
                .subcategory(category)
                .brand(brand)
                .addLink(links);
        return fromProduct(this.productService.create(tmp));
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
    public void updateBoughtCountPlus(List<Long> payload) {
        payload.forEach(this.productService::updateBoughtCountPlus);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateBoughtCountMinus(List<Long> payload) {
        payload.forEach(this.productService::updateBoughtCountMinus);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateBoughtCountPlus(Long productId) {
        this.productService.updateBoughtCountPlus(productId);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateBoughtCountMinus(Long productId) {
        this.productService.updateBoughtCountMinus(productId);
    }

}
