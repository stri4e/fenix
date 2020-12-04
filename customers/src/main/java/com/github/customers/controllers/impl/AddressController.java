package com.github.customers.controllers.impl;

import com.github.customers.controllers.IAddressController;
import com.github.customers.dto.AddressDto;
import com.github.customers.entity.Address;
import com.github.customers.entity.Customer;
import com.github.customers.services.IAddressService;
import com.github.customers.services.ICustomerService;
import com.github.customers.utils.Logging;
import com.github.customers.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.customers.utils.TransferObj.fromAddress;
import static com.github.customers.utils.TransferObj.toAddress;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/addresses")
public class AddressController implements IAddressController {

    private final IAddressService addressService;

    private final ICustomerService customerService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AddressDto findAddresses(Long addressId) {
        return fromAddress(this.addressService.readById(addressId));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public AddressDto save(Long customerId, AddressDto payload) {
        Customer customer = this.customerService.readById(customerId);
        Address address = this.addressService.create(toAddress(payload));
        this.customerService.update(customer.address(address));
        return fromAddress(address);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(AddressDto payload) {
        this.addressService.update(
                payload.getId(),
                payload.getCountry(),
                payload.getRegion(),
                payload.getCity(),
                payload.getStreet(),
                payload.getStreetNumber(),
                payload.getFlatNumber(),
                payload.getZipCode()
        );
    }

}
