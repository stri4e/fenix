package com.github.managers.services.impl;

import com.github.managers.dto.FilterDto;
import com.github.managers.services.IFiltersService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FiltersService implements IFiltersService {

    @Override
    public Optional<FilterDto> create(String subcategoryName, FilterDto payload) {
        return Optional.empty();
    }

    @Override
    public Optional<FilterDto> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(FilterDto payload) {

    }

    @Override
    public void delete(Long id) {

    }
}
