package com.github.products.controllers;

import com.github.products.dto.ProductDto;
import com.github.products.entity.EntityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

public interface IProductController {

    @GetMapping(path = "/page")
    Page<ProductDto> findProductsByPage(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "subcategory_name", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "create_at", direction = Sort.Direction.ASC),
            }) Pageable pageable);

    @GetMapping(path = "/page/{subcategory}")
    Page<ProductDto> findProductsByPage(
            @PathVariable String subcategory,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "create_at", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

    @GetMapping(path = "/page/{subcategory}/filters")
    Page<ProductDto> findProductsByPageAndFilters(
            @PathVariable String subcategory,
            @RequestParam MultiValueMap<String, String> filters,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "create_at", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<ProductDto> searchProduct(
            @NotBlank @RequestParam(name = "searchLine") String searchLine
    );

    @PostMapping(
            path = "/edit/{subcategory_name}/{brand_name}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    ProductDto save(@PathVariable(name = "subcategory_name") String subcategoryName,
                    @PathVariable(name = "brand_name") String brandName,
                    @Valid @RequestBody ProductDto payload
    );

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Object findByParams(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "ids", required = false) List<Long> ids
    );

    @PutMapping(
            path = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateProduct(@RequestBody ProductDto payload);

    @DeleteMapping(
            path = "/edit/{id}/{status}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateStatusProduct(
            @PathVariable Long id,
            @PathVariable EntityStatus status
    );

}
