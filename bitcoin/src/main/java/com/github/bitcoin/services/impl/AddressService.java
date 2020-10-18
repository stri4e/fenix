package com.github.bitcoin.services.impl;

import com.github.bitcoin.entity.Address;
import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.exceptions.NotFound;
import com.github.bitcoin.repository.AddressRepo;
import com.github.bitcoin.services.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService implements IAddressService {

    private final AddressRepo addressRepo;

    @Override
    public List<Address> createAll(List<Address> addresses) {
        return this.addressRepo.saveAll(addresses);
    }

    @Override
    public List<String> readAllAddresses(EntityStatus status) {
        return addressRepo.findAllAddresses(status);
    }

    @Override
    public Address readByAddress(String address) {
        return this.addressRepo.findByAddress(address)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Address address) {
        this.addressRepo.save(address);
    }

    @Override
    public void updateStatus(String address, EntityStatus status) {
        this.addressRepo.updateStatus(address, status);
    }

}
