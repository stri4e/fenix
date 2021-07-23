package com.github.products.services.impl;

import com.github.products.entity.Brand;
import com.github.products.entity.EntityStatus;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.exceptions.ParametersBadRequest;
import com.github.products.repository.BrandRepo;
import com.github.products.services.IBrandService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService implements IBrandService {

    private final BrandRepo brandRepo;

    @Override
    public Brand create(Brand entity) {
        if (Objects.isNull(entity)) {
            throw new EntityBadRequest();
        }
        return this.brandRepo.save(entity);
    }

    @Override
    public Brand readByName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new ParametersBadRequest();
        }
        return this.brandRepo.findByName(name)
                .orElseThrow(EntityNotFound::new);
    }

    @Override
    public Brand readById(Long id) {
        return this.brandRepo.findById(
                requiredId(id)
        ).orElseThrow(EntityNotFound::new);
    }

    @Override
    public List<Brand> readAllByStatus(EntityStatus status) {
        return this.brandRepo.findAllByStatus(status);
    }

    @Override
    public void update(Long id, String name) {
        if (Objects.isNull(id) || StringUtils.isBlank(name)) {
            throw new ParametersBadRequest();
        }
        this.brandRepo.updateName(id, name);
    }

    @Override
    public void delete(Long id) {
        this.brandRepo.updateStatus(
                requiredId(id), EntityStatus.off
        );
    }

    private Long requiredId(Long id) {
        return Optional.ofNullable(id)
                .orElseThrow(EntityBadRequest::new);
    }

}
