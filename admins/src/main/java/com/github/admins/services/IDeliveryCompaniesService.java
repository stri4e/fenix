package com.github.admins.services;

import com.github.admins.dto.CompanyDto;
import com.github.admins.services.impl.DeliveryCompaniesService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(
        name = "deliveries",
        fallback = DeliveryCompaniesService.class,
        contextId = "deliveryCompanyId"
)
public interface IDeliveryCompaniesService {

    @PostMapping(
            path = "/v1/companies/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    Optional<CompanyDto> save(@RequestBody CompanyDto payload);

    @GetMapping(
            path = "/v1/companies/fetch/{id}"
    )
    Optional<CompanyDto> findById(@PathVariable(name = "id") Long id);

    @PutMapping(
            path = "/v1/companies/edit"
    )
    void update(@RequestBody CompanyDto payload);

    @DeleteMapping(
            path = "/v1/companies/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void delete(@PathVariable(name = "id") Long id);

}
