package com.github.admins.services.impl;

import com.github.admins.dto.CriteriaDto;
import com.github.admins.services.ICriteriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriteriaService implements ICriteriaService {

    @Override
    public Optional<CriteriaDto> createToFilters(Long filterId, CriteriaDto payload) {
        return Optional.empty();
    }

    @Override
    public void createToProducts(Long productId, List<Long> payload) {

    }

    @Override
    public Optional<CriteriaDto> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(CriteriaDto payload) {

    }

    @Override
    public void updateInProducts(Long productId, Long criteriaId) {

    }

    @Override
    public void deleteInProducts(Long productId, Long criteriaId) {

    }

    @Override
    public void updateInFilters(Long filterId, Long criteriaId) {

    }

    @Override
    public void deleteInFilters(Long filterId, Long criteriaId) {

    }

    @Override
    public void delete(Long id) {

    }
}
