package com.github.products.services.impl;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Filter;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.repository.FiltersRepo;
import com.github.products.services.IFiltersService;
import com.github.products.utils.NullSafety;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.github.products.utils.NullSafety.requiredNotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class FiltersService implements IFiltersService {

    private final FiltersRepo filtersRepo;

    @Override
    public Filter create(Filter filter) {
        return this.filtersRepo.save(
                requiredNotNull(filter, EntityBadRequest::new)
        );
    }

    @Override
    public Filter readById(Long id) {
        return this.filtersRepo.findById(
                requiredNotNull(id, EntityBadRequest::new)
        ).orElseThrow(EntityNotFound::new);
    }

    @Override
    public void update(Filter filter) {
        this.filtersRepo.save(
                requiredNotNull(filter, EntityBadRequest::new)
        );
    }

    @Override
    public void updateTitle(Filter filter) {
        this.filtersRepo.updateTitle(
                requiredNotNull(filter, EntityBadRequest::new)
        );
    }

    @Override
    public void delete(Long id) {
        this.filtersRepo.updateStatus(
                requiredNotNull(id, EntityBadRequest::new), EntityStatus.off
        );
    }

}
