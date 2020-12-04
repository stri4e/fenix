package com.github.deliveries.services;

import com.github.deliveries.entity.Address;

public interface IAddressService {

    Address create(Address address);

    Address readById(Long id);

    void update(
            Long id,
            String country,
            String region,
            String city,
            String street,
            String streetNumber,
            String flatNumber,
            String zipCode
    );

}
