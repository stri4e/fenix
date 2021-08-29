package com.github.products.services;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Subcategory;

import java.util.List;

public interface ISubcategoryService {

    Subcategory create(Subcategory subCategory);

    List<Subcategory> readAllByStatus(EntityStatus status);

    Subcategory readByName(String name);

    Subcategory readById(Long id);

    Subcategory getById(Long id);

    void update(Subcategory subCategory);

    void updateSub(Subcategory subCategory);

    void delete(Long id);

}
