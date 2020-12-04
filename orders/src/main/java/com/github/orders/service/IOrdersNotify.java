package com.github.orders.service;

import com.github.orders.dto.OrderDetailDto;
import com.github.orders.service.impl.OrdersNotify;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "websocket",
        fallback = OrdersNotify.class
)
public interface IOrdersNotify {

    @PostMapping(
            path = "/v1/push/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    void orderNotify(@RequestBody OrderDetailDto payload);

}
