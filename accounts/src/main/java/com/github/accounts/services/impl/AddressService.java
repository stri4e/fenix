package com.github.accounts.services.impl;

import com.github.accounts.entity.Address;
import com.github.accounts.exceptions.NotFound;
import com.github.accounts.repository.AddressRepo;
import com.github.accounts.services.IAddressService;
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
    public void update(Long id, String country, String region, String city, String street,
                       String streetNumber, String flatNumber, String zipCode) {
        this.addressRepo.update(
                id, country, region, city, street,
                streetNumber, flatNumber, zipCode
        );
    }

}
