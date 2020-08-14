package com.github.orders.service;

import com.github.orders.dto.PurchaseDto;
import com.github.orders.service.impl.PurchaseService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "statistics",
        fallback = PurchaseService.class
)
public interface IPurchaseService {

    @PostMapping(
            path = "/v1/purchases/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    void createPurchase(@RequestBody PurchaseDto payload);

}
