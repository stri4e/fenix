package com.github.admins.services.impl;

import com.github.admins.dto.SubcategoryDto;
import com.github.admins.services.ISubcategoryService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class SubcategoryService implements ISubcategoryService {

    @Override
    public Optional<SubcategoryDto> create(String categoryName, @Valid SubcategoryDto payload) {
        return Optional.empty();
    }

    @Override
    public Optional<SubcategoryDto> readByName(String name) {
        return Optional.empty();
    }

    @Override
    public void update(@Valid SubcategoryDto payload) {

    }

    @Override
    public void delete(Long id) {

    }
}
