package com.github.admins.controllers.impl;

import com.github.admins.controllers.IFiltersController;
import com.github.admins.dto.FilterDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.exceptions.NotFound;
import com.github.admins.services.IFiltersService;
import com.github.admins.utils.Logging;
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
