package com.github.products.services;

import com.github.products.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> read();

    Category create(Category c);

    Category readByName(String name);

    Category readById(Long id);

    void update(Category c);

    void remove(Long id);

    List<Category> readAll();

}
