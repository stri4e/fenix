package com.github.products.services;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Subcategory;

import java.util.List;

public interface ISubcategoryService {

    Subcategory create(Subcategory subCategory);

    List<Subcategory> readAllByStatus(EntityStatus status);

    Subcategory readByName(String name);

    Subcategory readById(Long id);

    List<Subcategory> readAllByCategoryName(String categoryName);

    void update(Subcategory subCategory);

    void delete(Long id);

}
