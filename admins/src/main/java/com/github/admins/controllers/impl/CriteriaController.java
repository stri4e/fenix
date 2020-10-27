package com.github.admins.controllers.impl;

import com.github.admins.controllers.ICriteriaController;
import com.github.admins.dto.CriteriaDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.exceptions.NotFound;
import com.github.admins.services.ICriteriaService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/criteria")
@RequiredArgsConstructor
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
