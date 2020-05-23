package com.github.admins.services.impl;

import com.github.admins.payload.Category;
import com.github.admins.services.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Override
    public Optional<Category> create(Category c) {
        return Optional.empty();
    }

    @Override
    public Optional<Category> readByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Category>> readAll() {
        return Optional.empty();
    }

    @Override
    public void update(Category c) {

    }

    @Override
    public void remove(Long id) {

    }
}
