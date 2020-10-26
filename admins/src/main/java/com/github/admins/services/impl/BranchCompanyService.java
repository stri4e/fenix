package com.github.admins.services.impl;

import com.github.admins.dto.BranchDto;
import com.github.admins.services.IBranchCompanyService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchCompanyService implements IBranchCompanyService {

    @Override
    public Optional<BranchDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<BranchDto> create(Long companyId, BranchDto payload) {
        return Optional.empty();
    }

    @Override
    public void update(BranchDto payload) {

    }

    @Override
    public void delete(Long id) {

    }
}
