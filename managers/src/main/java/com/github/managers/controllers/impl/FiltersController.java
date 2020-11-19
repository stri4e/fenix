package com.github.managers.controllers.impl;

import com.github.managers.controllers.IFiltersController;
import com.github.managers.dto.FilterDto;
import com.github.managers.exceptions.BadRequest;
import com.github.managers.exceptions.NotFound;
import com.github.managers.services.IFiltersService;
import com.github.managers.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/filters")
public class FiltersController implements IFiltersController {

    private final IFiltersService filtersService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public FilterDto save(String subcategoryName, FilterDto payload) {
        return this.filtersService.create(subcategoryName, payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public FilterDto findById(Long id) {
        return this.filtersService.readById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(FilterDto payload) {
        this.filtersService.update(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.filtersService.delete(id);
    }
}
