package com.github.products.services;

import com.github.products.entity.Specification;

public interface ISpecificationService {

    Specification create(Specification s);

    Specification readById(Long id);

    void update(Specification s);

}
