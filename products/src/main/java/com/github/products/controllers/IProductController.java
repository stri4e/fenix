package com.github.products.controllers;

import com.github.products.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

}
