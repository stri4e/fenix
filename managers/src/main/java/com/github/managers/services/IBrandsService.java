package com.github.managers.services;

import com.github.managers.dto.BrandDto;
import com.github.managers.services.impl.BrandsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "products",
        fallback = BrandsService.class,
        contextId = "brandsId"
)
public interface IBrandsService {

    @PostMapping(
            path = "/v1/brands/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    Optional<BrandDto> create(@RequestBody BrandDto payload);

    @GetMapping(
            path = "/v1/brands/fetch/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<BrandDto> readByName(@PathVariable(name = "name") String name);

    @GetMapping(
            path = "/v1/brands/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<List<BrandDto>> readAllByStatus(
            @PathVariable(name = "status") String status
    );

    @PutMapping(
            path = "/v1/brands/edit"
    )
    void update(@RequestBody BrandDto payload);

    @DeleteMapping(
            path = "/v1/brands/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void delete(@PathVariable(name = "id") Long id);

}
