package com.github.orders.controllers.impl;

import com.github.orders.controllers.IAddressController;
import com.github.orders.dto.AddressDto;
import com.github.orders.entity.AddressType;
import com.github.orders.service.IAddressService;
import com.github.orders.utils.Logging;
import com.github.orders.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.github.orders.utils.TransferObj.fromAddress;
import static com.github.orders.utils.TransferObj.toAddress;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/addresses")
public class AddressController implements IAddressController {

    private final IAddressService addressService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<AddressDto> findAddresses(UUID userId) {
        return this.addressService.readByUserId(userId)
                .stream()
                .map(TransferObj::fromAddress)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AddressType[] findAddressType() {
        return AddressType.values();
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AddressDto save(UUID userId, AddressDto payload) {
        return fromAddress(this.addressService.create(toAddress(payload, userId)));
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
