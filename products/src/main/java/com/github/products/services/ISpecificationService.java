package com.github.products.services;

import com.github.products.entity.Specification;

import java.util.Collection;
import java.util.List;

public interface ISpecificationService {

    Specification create(Specification s);

    Specification readById(Long id);

    void update(Specification s);

    void delete(Long id);

    List<Specification> readDistinctByNameAndDescriptionContains(String name, String patter);

}
