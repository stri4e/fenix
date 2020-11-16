package com.github.admins.services.impl;

import com.github.admins.dto.FilterDto;
import com.github.admins.services.IFiltersService;
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
