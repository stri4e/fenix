package com.github.products.controllers.impl;

import com.github.products.controllers.IFiltersController;
import com.github.products.dto.FilterDto;
import com.github.products.entity.Filter;
import com.github.products.entity.Subcategory;
import com.github.products.services.IFiltersService;
import com.github.products.services.ISubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.products.utils.TransferObj.fromFilter;
import static com.github.products.utils.TransferObj.toFilter;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/filters")
public class FiltersController implements IFiltersController {

    private final IFiltersService filtersService;

    private final ISubCategoryService subCategoryService;

    @Override
    public FilterDto save(String subcategoryName, FilterDto payload) {
        Subcategory subcategory = this.subCategoryService
                .readByName(subcategoryName);
        Filter filter = this.filtersService.create(toFilter(payload));
        subcategory.addFilter(filter);
        return fromFilter(filter);
    }

    @Override
    public FilterDto findById(Long id) {
        return fromFilter(this.filtersService.readById(id));
    }

    @Override
    public void update(FilterDto payload) {
        Filter filter = this.filtersService.readById(payload.getId());
        filter.setTitle(payload.getTitle());
        filter.setCriteria(payload.getCriteria());
        this.filtersService.update(filter);
    }

    @Override
    public void remove(Long id) {
        this.filtersService.delete(id);
    }
}
