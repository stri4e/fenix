package com.github.products.services;

import com.github.products.entity.Filter;

public interface IFiltersService {

    Filter create(Filter filter);

    Filter readById(Long id);

    void update(Filter filter);

    void updateTitle(Filter filter);

    void delete(Long id);

}
