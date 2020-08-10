package com.github.orders.service;

import com.github.orders.dto.OrderDetailEntryDto;
import com.github.orders.service.impl.PushOrders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "websocket",
        fallback = PushOrders.class
)
public interface IPushOrders {

    @PostMapping(
            path = "/v1/push",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    void pushOrder(@RequestBody OrderDetailEntryDto payload);

}
