package com.github.deliveries.services.impl;

import com.github.deliveries.entity.Company;
import com.github.deliveries.entity.EntityStatus;
import com.github.deliveries.exceptions.NotFound;
import com.github.deliveries.repository.CompanyRepo;
import com.github.deliveries.services.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"company", "companies"})
public class CompanyService implements ICompanyService {

    private final CompanyRepo companyRepo;

    @Override
    @Caching(
            put = @CachePut(value = "company", key = "#company.id"),
            evict = @CacheEvict(value = "companies", allEntries = true)
    )
    public Company create(Company company) {
        return this.companyRepo.save(company);
    }

    @Override
    @Cacheable(value = "companies", unless = "#result.size() == 0")
    public List<Company> readAll() {
        return this.companyRepo.findAllByStatus(EntityStatus.on);
    }

    @Override
    @Cacheable(value = "company", key = "#id")
    public Company readById(Long id) {
        return this.companyRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    @Caching(
            put = @CachePut(value = "company", key = "#id"),
            evict = @CacheEvict(value = "companies", allEntries = true)
    )
    public void update(Long id, String name) {
        this.companyRepo.updateCompany(id, name);
    }

    @Override
    @Caching(
            put = @CachePut(value = "company", key = "#id"),
            evict = @CacheEvict(value = "companies", allEntries = true)
    )
    public void remove(Long id) {
        this.companyRepo.updateStatus(id, EntityStatus.off);
    }

}
