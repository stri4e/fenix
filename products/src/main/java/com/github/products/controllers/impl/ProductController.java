package com.github.products.controllers.impl;

import com.github.products.controllers.IProductController;
import com.github.products.dto.ProductDto;
import com.github.products.entity.Brand;
import com.github.products.entity.EntityStatus;
import com.github.products.entity.Product;
import com.github.products.entity.SubCategory;
import com.github.products.services.IBrandService;
import com.github.products.services.IProductService;
import com.github.products.services.ISubCategoryService;
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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.github.products.utils.TransferObj.fromProduct;
import static com.github.products.utils.TransferObj.toProduct;

@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class ProductController implements IProductController {

    private final IProductService productService;

    private final ISubCategoryService subCategoryService;

    private final IBrandService brandService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<ProductDto> getProduct(Pageable pageable) {
        Page<Product> products = this.productService.read(pageable);
        var total = products.getTotalElements();
        return new PageImpl<>(
                products.stream()
                        .map(TransferObj::fromProduct)
                        .collect(Collectors.toList()), pageable, total
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<ProductDto> getProduct(String category, Pageable pageable) {
        Page<Product> products = this.productService
                .readAllByCategory(category, pageable);
        long total = products.getTotalElements();
        return new PageImpl<>(
                products.stream()
                        .map(TransferObj::fromProduct)
                        .collect(Collectors.toList()), pageable, total
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
        SubCategory category = this.subCategoryService.readByName(subcategoryName);
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
        this.productService.updateProduct(product);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateStatusProduct(Long id, EntityStatus status) {
        this.productService.updateStatus(status, id);
    }

}
