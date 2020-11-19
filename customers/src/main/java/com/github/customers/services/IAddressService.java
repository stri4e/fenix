package com.github.customers.services;

import com.github.customers.entity.Address;

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
