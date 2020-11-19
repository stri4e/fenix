package com.github.orders.service;

import com.github.orders.dto.DeliveryDto;
import com.github.orders.service.impl.CustomerService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "deliveries",
        fallback = CustomerService.class
)
public interface IDeliveryService {

    @GetMapping(
            path = "/fetch/{deliveryId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<DeliveryDto> readById(
            @PathVariable(name = "deliveryId") Long deliveryId
    );

}
