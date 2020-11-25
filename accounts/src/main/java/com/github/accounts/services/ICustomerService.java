package com.github.accounts.services;

import com.github.accounts.dto.CustomerDto;
import com.github.accounts.services.impl.CustomerService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@FeignClient(
        name = "customers",
        fallback = CustomerService.class
)
public interface ICustomerService {

    @PostMapping(
            path = "/v1/edit/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    CustomerDto saveCustomer(
            @PathVariable(name = "userId") UUID userId,
            @RequestBody CustomerDto payload
    );

}
