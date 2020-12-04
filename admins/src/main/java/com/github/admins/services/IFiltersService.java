package com.github.admins.services;

import com.github.admins.dto.FilterDto;
import com.github.admins.services.impl.FiltersService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(
        name = "products",
        fallback = FiltersService.class,
        contextId = "filtersId"
)
public interface IFiltersService {

    @PostMapping(
            path = "/v1/filters/edit/{subcategoryName}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<FilterDto> create(
            @PathVariable(name = "subcategoryName") String subcategoryName,
            @RequestBody FilterDto payload
    );

    @GetMapping(
            path = "/v1/filters/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<FilterDto> readById(@PathVariable(name = "id") Long id);

    @PutMapping(
            path = "/v1/filters/edit"
    )
    void update(@RequestBody FilterDto payload);

    @DeleteMapping(
            path = "/v1/filters/edit/{id}"
    )
    void delete(@PathVariable(name = "id") Long id);

}
