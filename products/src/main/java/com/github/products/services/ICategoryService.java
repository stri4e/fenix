package com.github.products.services;

import com.github.products.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> find();

    Category create(Category c);

    Category findByName(String name);

    void update(Category c);

    void remove(Long id);

    List<Category> findAll();

}
