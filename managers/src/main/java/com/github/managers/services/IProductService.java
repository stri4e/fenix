package com.github.managers.services;

import com.github.managers.dto.ProductDto;
import com.github.managers.services.impl.ProductService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "products",
        fallback = ProductService.class,
        contextId = "productId"
)
public interface IProductService {

    @PostMapping(
            path = "/v1/edit/{subcategoryName}/{brandName}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<ProductDto> create(
            @PathVariable(name = "subcategoryName") String subcategoryName,
            @PathVariable(name = "brandName") String brandName,
            @RequestBody ProductDto p
    );

    @GetMapping(
            path = "/v1/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<ProductDto> readById(@PathVariable Long id);

    @GetMapping(
            path = "/v1/fetch/un-publish",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<List<ProductDto>> readAllUnPublish();

    @GetMapping(
            path = "/v1/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<List<ProductDto>> readByIds(@RequestParam List<Long> ids);

    @PutMapping(
            path = "/v1/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    void update(ProductDto p);

    @DeleteMapping(
            path = "/v1/edit/{id}/{status}"
    )
    void updateStatus(
            @PathVariable Long id,
            @PathVariable String status
    );

}
