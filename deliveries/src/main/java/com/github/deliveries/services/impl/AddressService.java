package com.github.deliveries.services.impl;

import com.github.deliveries.entity.Address;
import com.github.deliveries.exceptions.NotFound;
import com.github.deliveries.repository.AddressRepo;
import com.github.deliveries.services.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService implements IAddressService {

    private final AddressRepo addressRepo;

    @Override
    public Address create(Address address) {
        return this.addressRepo.save(address);
    }

    @Override
    public Address readById(Long id) {
        return this.addressRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long id, String country, String city, String street,
                       Integer streetNumber, Integer flatNumber, String state, Integer zipCode) {
        this.addressRepo.update(id, country, city, street,
                streetNumber, flatNumber, state, zipCode);
    }

}
