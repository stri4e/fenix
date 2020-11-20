package com.github.products.controllers;

import com.github.products.dto.ProductBoughtSign;
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

    @GetMapping(
            path = "/sign",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ProductBoughtSign[] findBoughtSign();

    @PutMapping(
            path = "/edit/many/bought/count/{sign}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateBoughtCount(
            @PathVariable(name = "sign") ProductBoughtSign sign,
            @RequestBody List<Long> payload
    );

    @PutMapping(
            path = "/edit/single/bought/count/{productId}/{sign}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateBoughtCount(
            @PathVariable(name = "sign") ProductBoughtSign sign,
            @PathVariable(name = "productId") Long productId
    );

}
