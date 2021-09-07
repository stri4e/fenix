package com.github.products.controllers;

import com.github.products.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IProductsPagesController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Page<ProductDto> searchProduct(@RequestParam(name = "searchLine") String searchLine);

    @GetMapping(path = "/page/popular")
    Page<ProductDto> findProductsByPage(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "boughtCount", direction = Sort.Direction.ASC),
            }) Pageable pageable);

    @GetMapping(path = "/page/{subcategory}")
    Page<ProductDto> findProductsByPage(
            @PathVariable String subcategory,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "createAt", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

    @GetMapping(path = "/page/{subcategory}/filters")
    Page<ProductDto> findProductsByPageAndFilters(
            @PathVariable String subcategory,
            @RequestParam(name = "criteria") List<Long> criteria,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "createAt", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

}
