package com.github.admins.services.impl;

import com.github.admins.payload.Category;
import com.github.admins.services.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    public static final Category EMPTY = new Category();

    @Override
    public Category create(Category c) {
        return EMPTY;
    }

    @Override
    public Category readByName(String name) {
        return EMPTY;
    }

    @Override
    public List<Category> readAll() {
        return Collections.emptyList();
    }

    @Override
    public void update(Category c) {

    }

    @Override
    public void remove(Long id) {

    }
}
