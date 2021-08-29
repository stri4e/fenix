package com.github.products.services;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Specification;

import java.util.Collection;
import java.util.List;

public interface ISpecificationService {

    Specification create(Specification s);

    Specification readById(Long id);

    Specification getById(Long id);

    List<Specification> readAllById(List<Long> ids);

    void update(Specification s);

    void updateSpec(Specification s);

    void delete(Long id);

    List<Specification> readAllBySubcategoryIdAndStatus(Long subcategoryId, EntityStatus status);

}
