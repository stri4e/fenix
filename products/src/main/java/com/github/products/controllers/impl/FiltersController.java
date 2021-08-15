package com.github.products.controllers.impl;

import com.github.products.controllers.IFiltersController;
import com.github.products.dto.FilterDto;
import com.github.products.entity.Filter;
import com.github.products.entity.Subcategory;
import com.github.products.services.IFiltersService;
import com.github.products.services.ISubcategoryService;
import com.github.products.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    private final ISubcategoryService subCategoryService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public FilterDto save(String subcategoryName, FilterDto payload) {
        return fromFilter(this.filtersService.create(
                toFilter(payload).addSub(this.subCategoryService.readByName(subcategoryName))
        ));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public FilterDto findById(Long id) {
        return fromFilter(this.filtersService.readById(id));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(FilterDto payload) {
        this.filtersService.updateTitle(toFilter(payload));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.filtersService.delete(id);
    }

}
