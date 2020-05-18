package com.github.admins.services;

import com.github.admins.payload.Category;
import com.github.admins.services.impl.CategoryService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "products-service",
        fallback = CategoryService.class
)
public interface ICategoryService {

    @PostMapping(
            path = "/v1/categories/edit",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    Category create(@RequestBody Category c);

    @GetMapping(
            path = "/v1/categories/edit/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Category readByName(@PathVariable String name);

    @GetMapping(
            path = "/v1/categories",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<Category> readAll();

    @PutMapping(
            path = "/v1/categories/edit"
    )
    void update(@RequestBody Category c);

    @DeleteMapping(
            path = "/v1/categories/edit/{id}"
    )
    void remove(@PathVariable Long id);

}
