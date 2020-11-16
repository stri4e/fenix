package com.github.orders.service;

import com.github.orders.entity.Address;

import java.util.List;
import java.util.UUID;

public interface IAddressService {

    Address create(Address address);

    List<Address> readByUserId(UUID userId);

    Address readById(Long id);

    void update(
            Long id,
            String country,
            String city,
            String street,
            Integer streetNumber,
            Integer flatNumber,
            String state,
            Integer zipCode
    );

}
