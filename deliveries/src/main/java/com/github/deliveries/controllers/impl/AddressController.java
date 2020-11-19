package com.github.deliveries.controllers.impl;

import com.github.deliveries.controllers.IAddressController;
import com.github.deliveries.dto.AddressDto;
import com.github.deliveries.entity.Address;
import com.github.deliveries.entity.Delivery;
import com.github.deliveries.services.IAddressService;
import com.github.deliveries.services.IDeliveryService;
import com.github.deliveries.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.deliveries.utils.TransferObj.fromAddress;
import static com.github.deliveries.utils.TransferObj.toAddress;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/addresses")
public class AddressController implements IAddressController {

    private final IAddressService addressService;

    private final IDeliveryService deliveryService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AddressDto findAddresses(Long addressId) {
        return fromAddress(this.addressService.readById(addressId));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AddressDto save(Long deliveryId, AddressDto payload) {
        Delivery delivery = this.deliveryService.readById(deliveryId);
        Address address = this.addressService.create(toAddress(payload));
        this.deliveryService.update(delivery.address(address));
        return fromAddress(address);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(AddressDto payload) {
        this.addressService.update(
                payload.getId(),
                payload.getCountry(),
                payload.getCity(),
                payload.getStreet(),
                payload.getStreetNumber(),
                payload.getFlatNumber(),
                payload.getState(),
                payload.getZipCode()
        );
    }

}
