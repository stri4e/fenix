package com.github.managers.services;

import com.github.managers.dto.SpecificationDto;
import com.github.managers.services.impl.SpecificationService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(
        name = "products",
        fallback = SpecificationService.class,
        contextId = "specificationId"
)
public interface ISpecificationService {

    @PostMapping(
            path = "/v1/specification/edit/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<SpecificationDto> create(
            @PathVariable(name = "productId") Long productId,
            @RequestBody SpecificationDto s
    );

    @GetMapping(
            path = "/v1/specification/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<SpecificationDto> readById(@PathVariable Long id);

    @PutMapping(
            path = "/v1/specification/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    void update(SpecificationDto s);

}
