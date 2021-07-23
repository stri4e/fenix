package com.github.products.services.impl;

import com.github.products.entity.Criteria;
import com.github.products.entity.EntityStatus;
import com.github.products.exceptions.NotFound;
import com.github.products.repository.CriteriaRepo;
import com.github.products.services.ICriteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CriteriaService implements ICriteriaService {

    private final CriteriaRepo criteriaRepo;

    @Override
    public Criteria create(Criteria criteria) {
        return this.criteriaRepo.save(criteria);
    }

    @Override
    public Criteria readById(Long id) {
        return this.criteriaRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public List<Criteria> readAll(List<Long> ids) {
        return this.criteriaRepo.findAllById(ids);
    }

    @Override
    public void update(Criteria c) {
        Criteria criteria = this.criteriaRepo.findById(c.getId())
                .orElseThrow(NotFound::new);
        criteria.setValue(c.getValue());
    }

    @Override
    public void updateStatus(Long id, EntityStatus status) {
        this.criteriaRepo.updateStatus(id, status);
    }
}
