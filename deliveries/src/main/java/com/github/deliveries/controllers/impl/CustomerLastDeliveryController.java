package com.github.deliveries.controllers.impl;

import com.github.deliveries.controllers.ICustomerLastDeliveryController;
import com.github.deliveries.dto.CustomerLastDeliveryDto;
import com.github.deliveries.services.ICustomerLastDeliveryService;
import com.github.deliveries.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.github.deliveries.utils.TransferObj.fromDelivery;
import static com.github.deliveries.utils.TransferObj.toDelivery;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/deliveries")
public class CustomerLastDeliveryController implements ICustomerLastDeliveryController {

    private final ICustomerLastDeliveryService deliveryService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public CustomerLastDeliveryDto findByUserId(UUID userId) {
        return fromDelivery(this.deliveryService.readByUserId(userId));
    }

    @Override
    public CustomerLastDeliveryDto findByUserId(Long deliveryId) {
        return fromDelivery(this.deliveryService.readById(deliveryId));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public CustomerLastDeliveryDto save(UUID userId, CustomerLastDeliveryDto payload) {
        return fromDelivery(
            this.deliveryService.create(toDelivery(payload, userId))
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateDelivery(CustomerLastDeliveryDto payload) {
        this.deliveryService.update(
                payload.getId(),
                payload.getType(),
                payload.getCompanyName()
        );
    }

}
