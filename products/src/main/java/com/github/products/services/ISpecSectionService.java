package com.github.products.services;

import com.github.products.entity.SpecificationSection;

import java.util.List;

public interface ISpecSectionService {

    SpecificationSection create(SpecificationSection ss);

    SpecificationSection readById(Long id);

    SpecificationSection getById(Long id);

    List<SpecificationSection> readAllByIds(List<Long> ids);

    void updateTitle(Long id, String title);

    void update(SpecificationSection section);

    void remove(Long id);

}
