package com.github.deliveries.services;

import com.github.deliveries.entity.Address;

public interface IAddressService {

    Address create(Address address);

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
