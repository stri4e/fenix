package com.github.admins.services;

import com.github.admins.payload.Product;
import com.github.admins.payload.ProductStatus;
import com.github.admins.services.impl.ProductService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(
        name = "products-service",
        fallback = ProductService.class
)
public interface IProductService {

    @PostMapping(
            path = "/v1/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Product create(Product p);

    @GetMapping(
            path = "/v1/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Product readById(Long id);

    @GetMapping(
            path = "/v1/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<Product> readAllUnPublish();

    @GetMapping(
            path = "/v1/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<Product> readByIds(List<Long> ids);

    @PutMapping(
            path = "/v1",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    void update(Product p);

    @PutMapping(
            path = "/v1/{id}/{status}"
    )
    void updateStatus(
            @PathVariable Long id,
            @PathVariable ProductStatus status
    );

}
