package com.github.products.services.impl;

import com.github.products.entity.Specification;
import com.github.products.exceptions.BadRequest;
import com.github.products.exceptions.NotFound;
import com.github.products.repository.SpecificationRepo;
import com.github.products.services.ISpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecificationService implements ISpecificationService {

    private final SpecificationRepo specificationRepo;

    @Override
    public Specification create(Specification s) {
        if (Objects.isNull(s)) {
            throw new BadRequest();
        }
        return this.specificationRepo.save(s);
    }

    @Override
    public Specification readById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequest();
        }
        return this.specificationRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Specification s) {
        this.specificationRepo.save(s);
    }
}
