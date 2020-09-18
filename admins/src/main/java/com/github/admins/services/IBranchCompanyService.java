package com.github.admins.services;

import com.github.admins.payload.Branch;
import com.github.admins.services.impl.BranchCompanyService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(
        name = "orders-service",
        fallback = BranchCompanyService.class,
        contextId = "branchId"
)
public interface IBranchCompanyService {

    @GetMapping(
            path = "/v1/delivery/company/branch/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<Branch> findById(@PathVariable Long id);

    @PostMapping(
            path = "/v1/delivery/company/branch/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<Branch> save(@RequestBody Branch payload);

    @PutMapping(
            path = "/v1/delivery/company/branch/edit"
    )
    void update(@RequestBody Branch payload);

    @DeleteMapping(
            path = "/v1/delivery/company/branch/edit/{id}"
    )
    void delete(@PathVariable(name = "id") Long id);

}
