package com.github.products.services;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.SubCategory;

import java.util.List;

public interface ISubCategoryService {

    SubCategory create(SubCategory subCategory);

    List<SubCategory> readAllByStatus(EntityStatus status);

    SubCategory readByName(String name);

    SubCategory readById(Long id);

    List<SubCategory> readAllByCategoryName(String categoryName);

    void update(SubCategory subCategory);

    void delete(Long id);

}
