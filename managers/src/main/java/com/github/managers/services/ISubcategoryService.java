package com.github.managers.services;

import com.github.managers.dto.SubcategoryDto;
import com.github.managers.services.impl.SubcategoryService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@FeignClient(
        name = "products",
        fallback = SubcategoryService.class,
        contextId = "subcategoryId"
)
public interface ISubcategoryService {

    @PostMapping(
            path = "/v1/subcategories/edit/{categoryName}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<SubcategoryDto> create(
            @PathVariable(name = "categoryName") String categoryName,
            @Valid @RequestBody SubcategoryDto payload
    );

    @GetMapping(
            path = "/v1/subcategories/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<SubcategoryDto> readByName(
            @RequestParam(name = "name") String name
    );

    @PutMapping(
            path = "/v1/subcategories/edit"
    )
    void update(@Valid @RequestBody SubcategoryDto payload);

    @DeleteMapping(
            path = "/v1/subcategories/edit/{id}"
    )
    void delete(@PathVariable Long id);

}
