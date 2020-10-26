package com.github.admins.services;

import com.github.admins.dto.SubcategoryDto;
import com.github.admins.payload.EntityStatus;
import com.github.admins.services.impl.CategoryService;
import com.github.admins.services.impl.SubcategoryService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@FeignClient(
        name = "products-service",
        fallback = SubcategoryService.class,
        contextId = "subcategoryId"
)
public interface ISubcategoryService {

    @PostMapping(
            path = "/v1/subcategories/edit/{categoryName}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    Optional<SubcategoryDto> create(
            @PathVariable(name = "categoryName") String categoryName,
            @Valid @RequestBody SubcategoryDto payload
    );

    @GetMapping(
            path = "/v1/subcategories/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Optional<SubcategoryDto> readByName(
            @RequestParam(name = "name", required = false) String name
    );

    @PutMapping(
            path = "/v1/subcategories/edit"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@Valid @RequestBody SubcategoryDto payload);

    @DeleteMapping(
            path = "/v1/subcategories/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void delete(@PathVariable Long id);

}
