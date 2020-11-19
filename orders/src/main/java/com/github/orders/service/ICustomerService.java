package com.github.orders.service;

import com.github.orders.dto.CustomerDto;
import com.github.orders.service.impl.CustomerService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customers",
        fallback = CustomerService.class
)
public interface ICustomerService {

    @GetMapping(
            path = "/fetch/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<CustomerDto> readById(
            @PathVariable(name = "customerId") Long customerId
    );

}
