package com.github.products.services;

import com.github.products.entity.Brand;
import com.github.products.entity.EntityStatus;

import java.util.List;

public interface IBrandService {

    Brand create(Brand brand);

    Brand readByName(String name);

    Brand readById(Long id);

    List<Brand> readAllByStatus(EntityStatus status);

    void update(Long id, String name);

    void delete(Long id);

}
