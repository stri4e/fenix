package com.github.products.services.impl;

import com.github.products.entity.SpecSection;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.repository.SpecSectionRepo;
import com.github.products.services.ISpecSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.github.products.utils.NullSafety.*;

@Service
@RequiredArgsConstructor
public class SpecSectionService implements ISpecSectionService {

    private final SpecSectionRepo specSectionRepo;

    @Override
    public SpecSection create(SpecSection specSection) {
        return this.specSectionRepo.save(
                requiredNotNull(specSection, EntityBadRequest::new)
        );
    }

    @Override
    public SpecSection findById(Long id) {
        return this.specSectionRepo.findById(
                requiredNotNull(id, EntityBadRequest::new)
        ).orElseThrow(EntityNotFound::new);
    }

    @Override
    public void updateTitle(Long id, String title) {
        this.specSectionRepo.updateTitle(
                requiredNotNull(id, EntityBadRequest::new),
                requiredNotBlank(title, EntityBadRequest::new)
        );
    }

    @Override
    public void update(SpecSection specSection) {
        this.specSectionRepo.save(
                requiredNotNull(specSection, EntityBadRequest::new)
        );
    }

    @Override
    public void remove(Long id) {
        this.specSectionRepo.deleteById(
                requiredNotNull(id, EntityBadRequest::new)
        );
    }
}
