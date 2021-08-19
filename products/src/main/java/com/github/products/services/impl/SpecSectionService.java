package com.github.products.services.impl;

import com.github.products.entity.SpecSection;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.repository.SpecSectionRepo;
import com.github.products.services.ISpecSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecSectionService implements ISpecSectionService {

    private final SpecSectionRepo specSectionRepo;

    @Override
    public SpecSection create(SpecSection ss) {
        return this.specSectionRepo.save(ss);
    }

    @Override
    public SpecSection findById(Long id) {
        return this.specSectionRepo.findById(id)
                .orElseThrow(EntityNotFound::new);
    }

    @Override
    public void updateTitle(Long id, String title) {
        this.specSectionRepo.updateTitle(id, title);
    }

    @Override
    public void update(SpecSection ss) {
        this.specSectionRepo.save(ss);
    }

    @Override
    public void remove(Long id) {
        this.specSectionRepo.deleteById(id);
    }
}
