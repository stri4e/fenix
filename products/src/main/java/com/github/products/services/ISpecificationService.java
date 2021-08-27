package com.github.products.services;

import com.github.products.entity.Specification;

import java.util.Collection;
import java.util.List;

public interface ISpecificationService {

    Specification create(Specification s);

    Specification readById(Long id);

    List<Specification> readAllById(List<Long> ids);

    void update(Specification s);

    void updateSpec(Specification s);

    void delete(Long id);

    List<Specification> readByParams(String name, String patter);

}
