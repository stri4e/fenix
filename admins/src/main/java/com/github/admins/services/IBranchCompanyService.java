package com.github.admins.services;

import com.github.admins.dto.BranchDto;
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
    Optional<BranchDto> findById(@PathVariable Long id);

    @PostMapping(
            path = "/v1/delivery/company/branch/edit/{companyId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<BranchDto> create(
            @PathVariable(name = "companyId") Long companyId,
            @RequestBody BranchDto payload
    );

    @PutMapping(
            path = "/v1/delivery/company/branch/edit"
    )
    void update(@RequestBody BranchDto payload);

    @DeleteMapping(
            path = "/v1/delivery/company/branch/edit/{id}"
    )
    void delete(@PathVariable(name = "id") Long id);

}
