package com.github.managers.services.impl;

import com.github.managers.dto.SpecificationDto;
import com.github.managers.services.ISpecificationService;
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
