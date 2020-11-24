package com.github.orders.service;

import com.github.orders.service.impl.CustomerService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(
        name = "customers",
        fallback = CustomerService.class,
        contextId = "customerStatisticId"
)
public interface ICustomerStatisticsService {

    @PutMapping(
            path = "/v1/statistics/edit/total/orders/{customerId}/{totalOrders}"
    )
    void updateTotalOrders(
            @PathVariable(name = "customerId") Long customerId,
            @PathVariable(name = "totalOrders") Integer totalOrders

    );

    @PutMapping(
            path = "/v1/statistics/edit/success/orders/{customerId}/{successOrders}"
    )
    void updateSuccessOrders(
            @PathVariable(name = "customerId") Long customerId,
            @PathVariable(name = "successOrders") Integer successOrders
    );

    @PutMapping(
            path = "/v1/statistics/edit/returned/orders/{customerId}/{returnedOrders}"
    )
    void updateReturnedOrders(
            @PathVariable(name = "customerId") Long customerId,
            @PathVariable(name = "returnedOrders") Integer returnedOrders
    );

}
