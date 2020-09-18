package com.github.admins.services.impl;

import com.github.admins.payload.Branch;
import com.github.admins.services.IBranchCompanyService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchCompanyService implements IBranchCompanyService {

    @Override
    public Optional<Branch> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Branch> save(Branch payload) {
        return Optional.empty();
    }

    @Override
    public void update(Branch payload) {

    }

    @Override
    public void delete(Long id) {

    }
}
