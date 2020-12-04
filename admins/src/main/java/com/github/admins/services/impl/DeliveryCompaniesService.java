package com.github.admins.services.impl;

import com.github.admins.dto.CompanyDto;
import com.github.admins.services.IDeliveryCompaniesService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryCompaniesService implements IDeliveryCompaniesService {

    @Override
    public Optional<CompanyDto> save(CompanyDto payload) {
        return Optional.empty();
    }

    @Override
    public Optional<CompanyDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(CompanyDto payload) {

    }

    @Override
    public void delete(Long id) {

    }
}
