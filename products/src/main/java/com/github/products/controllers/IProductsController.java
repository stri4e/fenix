package com.github.products.controllers;

import com.github.products.dto.ProductDto;
import com.github.products.entity.EntityStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

public interface IProductsController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<ProductDto> searchProduct(
            @NotBlank @RequestParam(name = "searchLine") String searchLine
    );

    @PostMapping(
            path = "/edit/{subcategory_name}/{brand_name}",
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
    void updateProduct(@Valid @RequestBody ProductDto payload);

    @DeleteMapping(
            path = "/edit/{id}/{status}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateStatusProduct(
            @PathVariable Long id,
            @PathVariable EntityStatus status
    );

    @PutMapping(
            path = "/many/bought/count/plus",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateBoughtCountPlus(@Valid @RequestBody List<Long> payload);

    @PutMapping(
            path = "/many/bought/count/minus",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateBoughtCountMinus(@Valid @RequestBody List<Long> payload);

    @PutMapping(
            path = "/single/bought/count/plus/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateBoughtCountPlus(
            @PathVariable(name = "productId") Long productId
    );

    @PutMapping(
            path = "/single/bought/count/minus/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateBoughtCountMinus(
            @PathVariable(name = "productId") Long productId
    );

}
