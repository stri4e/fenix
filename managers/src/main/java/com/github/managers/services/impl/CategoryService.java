package com.github.managers.services.impl;

import com.github.managers.dto.CategoryDto;
import com.github.managers.services.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Override
    public Optional<CategoryDto> create(CategoryDto c) {
        return Optional.empty();
    }

    @Override
    public Optional<CategoryDto> readByName(String categoryName) {
        return Optional.empty();
    }

    @Override
    public Optional<List<CategoryDto>> readAll() {
        return Optional.empty();
    }

    @Override
    public void update(CategoryDto c) {

    }

    @Override
    public void delete(Long id) {

    }
}
