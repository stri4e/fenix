package com.github.orders.service;

import com.github.orders.dto.ProductDto;
import com.github.orders.service.impl.ProductService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "products",
        fallback = ProductService.class
)
public interface IProductService {

    @GetMapping(
            path = "/v1/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<List<ProductDto>> readByIds(@RequestParam(name = "ids") List<Long> ids);

    @PutMapping(
            path = "/many/bought/count/minus",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateBoughtCountMinus(@RequestBody List<Long> payload);

    @PutMapping(
            path = "/single/bought/count/minus/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateBoughtCountMinus(
            @PathVariable(name = "productId") Long productId
    );

}
