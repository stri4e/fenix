package com.github.websocket.services;

import com.github.websocket.payload.Product;
import com.github.websocket.services.impl.ProductService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    List<Product> readByIds(@RequestParam List<Long> ids);

}
