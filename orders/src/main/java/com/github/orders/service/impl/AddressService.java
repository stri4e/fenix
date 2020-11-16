package com.github.orders.service.impl;

import com.github.orders.entity.Address;
import com.github.orders.exceptions.NotFound;
import com.github.orders.repository.AddressRepo;
import com.github.orders.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

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
    public List<Address> readByUserId(UUID userId) {
        return this.addressRepo.findByUserId(userId);
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
