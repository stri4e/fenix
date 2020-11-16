package com.github.orders.controllers.impl;

import com.github.orders.controllers.IDeliveryController;
import com.github.orders.dto.DeliveryDto;
import com.github.orders.entity.Address;
import com.github.orders.service.IAddressService;
import com.github.orders.service.IDeliveryService;
import com.github.orders.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.github.orders.utils.TransferObj.fromDelivery;
import static com.github.orders.utils.TransferObj.toDelivery;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/deliveries")
public class DeliveryController implements IDeliveryController {

    private final IDeliveryService deliveryService;

    private final IAddressService addressService;

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
        Address address = this.addressService.readById(payload.getAddress().getId());
        return fromDelivery(
            this.deliveryService.create(toDelivery(payload, userId).address(address))
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
