package com.github.managers.controllers.impl;

import com.github.managers.controllers.ICriteriaController;
import com.github.managers.dto.CriteriaDto;
import com.github.managers.exceptions.BadRequest;
import com.github.managers.exceptions.NotFound;
import com.github.managers.services.ICriteriaService;
import com.github.managers.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/criteria")
public class CriteriaController implements ICriteriaController {

    private final ICriteriaService criteriaService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public CriteriaDto saveToFilters(Long filterId, CriteriaDto payload) {
        return this.criteriaService.createToFilters(filterId, payload)
                .orElseThrow(BadRequest::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void saveToProducts(Long productId, List<Long> payload) {
        this.criteriaService.createToProducts(productId, payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public CriteriaDto findById(Long id) {
        return this.criteriaService.readById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(CriteriaDto payload) {
        this.criteriaService.update(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateInProducts(Long productId, Long criteriaId) {
        this.criteriaService.updateInProducts(productId, criteriaId);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void removeInProducts(Long productId, Long criteriaId) {
        this.criteriaService.deleteInProducts(productId, criteriaId);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateInFilters(Long filterId, Long criteriaId) {
        this.criteriaService.updateInFilters(filterId, criteriaId);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void removeInFilters(Long filterId, Long criteriaId) {
        this.criteriaService.deleteInFilters(filterId, criteriaId);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.criteriaService.delete(id);
    }
}
