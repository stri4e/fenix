package com.github.deliveries.controllers.impl;

import com.github.deliveries.controllers.IDeliveryController;
import com.github.deliveries.dto.DeliveryDto;
import com.github.deliveries.services.IDeliveryService;
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
public class DeliveryController implements IDeliveryController {

    private final IDeliveryService deliveryService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public DeliveryDto findDelivery(UUID userId) {
        return fromDelivery(this.deliveryService.readByUserId(userId));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public DeliveryDto save(UUID userId, DeliveryDto payload) {
        return fromDelivery(
            this.deliveryService.create(toDelivery(payload, userId))
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateDelivery(DeliveryDto payload) {
        this.deliveryService.update(
                payload.getId(),
                payload.getType(),
                payload.getCompanyName(),
                payload.getAmount()
        );
    }

}
