package com.github.admins.services.impl;

import com.github.admins.payload.Specification;
import com.github.admins.services.ISpecificationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpecificationService implements ISpecificationService {

    @Override
    public Optional<Specification> create(Specification s) {
        return Optional.empty();
    }

    @Override
    public Optional<Specification> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Specification s) {

    }

}
