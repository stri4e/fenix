package com.github.admins.services;

import com.github.admins.payload.Specification;
import com.github.admins.services.impl.SpecificationService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(
        name = "products-service",
        fallback = SpecificationService.class
)
public interface ISpecificationService {

    @PostMapping(
            path = "/v1/specification/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Specification create(Specification s);

    @GetMapping(
            path = "/v1/specification/info",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Specification readById(Long id);

    @PutMapping(
            path = "/v1/specification/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    void update(Specification s);

}
