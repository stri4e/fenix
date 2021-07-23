package com.github.products.services.impl;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Filter;
import com.github.products.exceptions.NotFound;
import com.github.products.repository.FiltersRepo;
import com.github.products.services.IFiltersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FiltersService implements IFiltersService {

    private final FiltersRepo filtersRepo;

    @Override
    public Filter create(Filter filter) {
        return this.filtersRepo.save(filter);
    }

    @Override
    public Filter readById(Long id) {
        return this.filtersRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Filter filter) {
        this.filtersRepo.save(filter);
    }

    @Override
    public void updateTitle(Filter filter) {
        this.filtersRepo.updateTitle(filter);
    }

    @Override
    public void delete(Long id) {
        this.filtersRepo.updateStatus(id, EntityStatus.off);
    }
}
