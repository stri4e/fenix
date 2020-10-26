package com.github.admins.services;

import com.github.admins.dto.FilterDto;
import com.github.admins.services.impl.FiltersService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(
        name = "products-service",
        fallback = FiltersService.class,
        contextId = "filtersId"
)
public interface IFiltersService {

    @PostMapping(
            path = "/edit/{subcategoryName}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<FilterDto> create(
            @PathVariable(name = "subcategoryName") String subcategoryName,
            @RequestBody FilterDto payload
    );

    @GetMapping(
            path = "/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<FilterDto> readById(@PathVariable(name = "id") Long id);

    @PutMapping(
            path = "/edit"
    )
    void update(@RequestBody FilterDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    void delete(@PathVariable(name = "id") Long id);

}
