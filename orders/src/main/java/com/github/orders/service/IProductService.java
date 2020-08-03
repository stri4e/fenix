package com.github.orders.service;

import com.github.orders.payload.Product;
import com.github.orders.service.impl.ProductService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "products-service",
        fallback = ProductService.class
)
public interface IProductService {

    @GetMapping(
            path = "/v1/fetch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<List<Product>> readByIds(@RequestParam List<Long> ids);

}
