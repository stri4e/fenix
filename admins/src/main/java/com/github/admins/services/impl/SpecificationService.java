package com.github.admins.services.impl;

import com.github.admins.payload.Specification;
import com.github.admins.services.ISpecificationService;
import org.springframework.stereotype.Service;

@Service
public class SpecificationService implements ISpecificationService {

    private static final Specification EMPTY = new Specification();

    @Override
    public Specification create(Specification s) {
        return EMPTY;
    }

    @Override
    public Specification readById(Long id) {
        return EMPTY;
    }

    @Override
    public void update(Specification s) {

    }

}
