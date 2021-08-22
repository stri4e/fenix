package com.github.products.services;

import com.github.products.entity.SpecSection;

import java.util.List;

public interface ISpecSectionService {

    SpecSection create(SpecSection ss);

    SpecSection findById(Long id);

    List<SpecSection> readAllByIds(List<Long> ids);

    void updateTitle(Long id, String title);

    void update(SpecSection ss);

    void remove(Long id);

}
