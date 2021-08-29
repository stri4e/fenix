package com.github.products.controllers;

import com.github.products.dto.ProductDto;
import com.github.products.entity.EntityStatus;
import com.github.products.utils.ProductBoughtCount;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

public interface IProductsController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<ProductDto> searchProduct(@RequestParam(name = "searchLine") String searchLine);

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    ProductDto save(@Valid @RequestBody ProductDto payload);

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
            path = "/bought/count/{bought}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateBoughtCount(
            @PathVariable(name = "bought") ProductBoughtCount bought,
            @Valid @RequestBody List<Long> payload
    );

}
