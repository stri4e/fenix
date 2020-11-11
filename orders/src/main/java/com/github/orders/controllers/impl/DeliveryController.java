package com.github.orders.controllers.impl;

import com.github.orders.controllers.IDeliveryController;
import com.github.orders.dto.DeliveryDto;
import com.github.orders.service.IDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.github.orders.utils.TransferObj.fromDelivery;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/deliveries")
public class DeliveryController implements IDeliveryController {

    private final IDeliveryService deliveryService;

    @Override
    public DeliveryDto findDelivery(UUID userId) {
        return fromDelivery(this.deliveryService.readByUserId(userId));
    }

    @Override
    public void updateDelivery(DeliveryDto payload) {
        this.deliveryService.update(
                payload.getId(),
                payload.getType(),
                payload.getCompanyName(),
                payload.getAddress(),
                payload.getAmount()
        );
    }
}
