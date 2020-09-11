package com.github.orders.service.impl;

import com.github.orders.entity.Company;
import com.github.orders.entity.CompanyStatus;
import com.github.orders.exceptions.NotFound;
import com.github.orders.repository.CompanyRepo;
import com.github.orders.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService implements ICompanyService {

    private final CompanyRepo companyRepo;

    @Override
    public Company create(Company company) {
        return this.companyRepo.save(company);
    }

    @Override
    public List<Company> readAll() {
        return this.companyRepo.findAllByStatus(CompanyStatus.active);
    }

    @Override
    public Company readById(Long id) {
        return this.companyRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Company company) {
        this.companyRepo.save(company);
    }

    @Override
    public void remove(Long id) {
        this.companyRepo.updateStatus(id, CompanyStatus.no_active);
    }
}
