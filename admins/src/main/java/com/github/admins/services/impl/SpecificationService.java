package com.github.admins.services.impl;

import com.github.admins.dto.SpecificationDto;
import com.github.admins.services.ISpecificationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpecificationService implements ISpecificationService {

    @Override
    public Optional<SpecificationDto> create(Long productId, SpecificationDto s) {
        return Optional.empty();
    }

    @Override
    public Optional<SpecificationDto> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(SpecificationDto s) {

    }
}
