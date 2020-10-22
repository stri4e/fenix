package com.github.payments.service;

import com.github.payments.service.impl.OrdersService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(
        name = "orders-service",
        fallback = OrdersService.class,
        contextId = "orderId"
)
public interface IOrdersService {

    @PutMapping(
            path = "/v1/edit/paid/{billId}"
    )
    void update(@PathVariable Long billId);

}
