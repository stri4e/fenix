package com.github.admins.services;

import com.github.admins.dto.EthereumContractDto;
import com.github.admins.services.impl.EthereumContractService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@FeignClient(
        name = "ethereum",
        fallback = EthereumContractService.class,
        contextId = "ethereumContractId"
)
public interface IEthereumContractService {

    @PostMapping(
            path = "/v1/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<EthereumContractDto> create(@Valid @RequestBody EthereumContractDto payload);

    @PutMapping(
            path = "/v1/edit"
    )
    void update(@RequestBody EthereumContractDto payload);

    @DeleteMapping(
            path = "/v1/fetch/{id}"
    )
    void delete(@PathVariable(name = "id") Long id);

}
