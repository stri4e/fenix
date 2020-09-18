package com.github.admins.services.impl;

import com.github.admins.payload.Company;
import com.github.admins.services.IDeliveryCompaniesService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryCompaniesService implements IDeliveryCompaniesService {

    @Override
    public Optional<Company> save(Company payload) {
        return Optional.empty();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Company payload) {

    }

    @Override
    public void delete(Long id) {

    }
}
