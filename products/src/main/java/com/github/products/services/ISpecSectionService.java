package com.github.products.services;

import com.github.products.entity.SpecSection;

public interface ISpecSectionService {

    SpecSection create(SpecSection ss);

    SpecSection findById(Long id);

    void updateTitle(Long id, String title);

    void update(SpecSection ss);

    void remove(Long id);

}
