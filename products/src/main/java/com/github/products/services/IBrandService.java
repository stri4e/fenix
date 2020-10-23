package com.github.products.services;

import com.github.products.entity.Brand;
import com.github.products.entity.EntityStatus;

import java.util.List;

public interface IBrandService {

    Brand create(Brand brand);

    Brand findByName(String name);

    Brand findById(Long id);

    List<Brand> readAllByStatus(EntityStatus status);

    void update(Brand brand);

    void delete(Long id);

}
