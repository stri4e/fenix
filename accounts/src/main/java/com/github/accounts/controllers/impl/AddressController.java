package com.github.accounts.controllers.impl;

import com.github.accounts.controllers.IAddressController;
import com.github.accounts.dto.AddressDto;
import com.github.accounts.services.IAddressService;
import com.github.accounts.utils.Logging;
import com.github.accounts.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.accounts.utils.TransferObj.fromAddress;
import static com.github.accounts.utils.TransferObj.toAddress;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/addresses")
public class AddressController implements IAddressController {

    private final IAddressService addressService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AddressDto findAddress(Long addressId) {
        return TransferObj.fromAddress(this.addressService.readById(addressId));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AddressDto save(AddressDto payload) {
        return fromAddress(this.addressService.create(toAddress(payload)));
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
