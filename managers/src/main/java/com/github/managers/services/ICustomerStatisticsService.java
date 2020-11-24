package com.github.managers.services;

import com.github.managers.dto.CustomerStatisticsDto;
import com.github.managers.services.impl.CustomerStatisticsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "customers",
        fallback = CustomerStatisticsService.class,
        contextId = "customerStatisticsId"
)
public interface ICustomerStatisticsService {

    @GetMapping(
            path = "/v1/statistics/fetch/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CustomerStatisticsDto findById(@PathVariable(name = "customerId") Long customerId);

}
