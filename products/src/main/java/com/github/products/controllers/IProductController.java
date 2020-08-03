package com.github.products.controllers;

import com.github.products.dto.ProductDto;
import com.github.products.entity.Product;
import com.github.products.entity.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IProductController {

    @GetMapping(path = "/page")
    Page<ProductDto> getProduct(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "category_name", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "createDate", direction = Sort.Direction.ASC),
            }) Pageable pageable);

    @GetMapping(path = "/page/{category}")
    Page<ProductDto> getProduct(
            @PathVariable String category,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "createDate", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

    @PostMapping(
            path = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    Product createProduct(@Valid @RequestBody Product payload);

    @GetMapping(
            path = "/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Object readByParams(
            @PathVariable(name = "id", required = false) Long id,
            @RequestParam(name = "values", required = false) List<Long> ids
    );

    @PutMapping(
            path = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(Product payload);

    @PutMapping(
            path = "/edit/{id}/{status}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateStatus(
            @PathVariable Long id,
            @PathVariable ProductStatus status
    );

}
