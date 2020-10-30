package com.github.products.services.impl;

import com.github.products.entity.Brand;
import com.github.products.entity.EntityStatus;
import com.github.products.exceptions.NotFound;
import com.github.products.repository.BrandRepo;
import com.github.products.services.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService implements IBrandService {

    private final BrandRepo brandRepo;

    @Override
    public Brand create(Brand brand) {
        return this.brandRepo.save(brand);
    }

    @Override
    public Brand readByName(String name) {
        return this.brandRepo.findByName(name)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Brand readById(Long id) {
        return this.brandRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public List<Brand> readAllByStatus(EntityStatus status) {
        return this.brandRepo.findAllByStatus(status);
    }

    @Override
    public void update(Brand brand) {
        this.brandRepo.save(brand);
    }

    @Override
    public void delete(Long id) {
        this.brandRepo.updateStatus(id, EntityStatus.off);
    }
}
