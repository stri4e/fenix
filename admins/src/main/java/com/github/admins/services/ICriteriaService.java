package com.github.admins.services;

import com.github.admins.dto.CriteriaDto;
import com.github.admins.services.impl.CriteriaService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "products",
        fallback = CriteriaService.class,
        contextId = "criteriaId"
)
public interface ICriteriaService {

    @PostMapping(
            path = "/v1/criteria/edit/to/filters/{filterId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    Optional<CriteriaDto> createToFilters(
            @PathVariable(name = "filterId") Long filterId,
            @RequestBody CriteriaDto payload
    );

    @PostMapping(
            path = "/v1/criteria/edit/to/products/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    void createToProducts(
            @PathVariable(name = "productId") Long productId,
            @RequestBody List<Long> payload
    );

    @GetMapping(
            path = "/v1/criteria/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<CriteriaDto> readById(@PathVariable Long id);

    @PutMapping(
            path = "/v1/criteria/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@RequestBody CriteriaDto payload);

    @PutMapping(
            path = "/v1/criteria/edit/in/products/{productId}/{criteriaId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateInProducts(
            @PathVariable(name = "productId") Long productId,
            @PathVariable(name = "criteriaId")  Long criteriaId
    );

    @DeleteMapping(
            path = "/v1/criteria/edit/in/products/{productId}/{criteriaId}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteInProducts(
            @PathVariable(name = "productId") Long productId,
            @PathVariable(name = "criteriaId")  Long criteriaId
    );

    @PutMapping(
            path = "/v1/criteria/edit/in/filters/{filterId}/{criteriaId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateInFilters(
            @PathVariable(name = "filterId") Long filterId,
            @PathVariable(name = "criteriaId")  Long criteriaId
    );

    @DeleteMapping(
            path = "/v1/criteria/edit/in/filters/{filterId}/{criteriaId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteInFilters(
            @PathVariable(name = "filterId") Long filterId,
            @PathVariable(name = "criteriaId")  Long criteriaId
    );

    @DeleteMapping(
            path = "/v1/criteria/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);

}
