package com.github.products.services.impl;

import com.github.products.entity.Criteria;
import com.github.products.entity.EntityStatus;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.repository.CriteriaRepo;
import com.github.products.services.ICriteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.github.products.utils.NullSafety.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CriteriaService implements ICriteriaService {

    private final CriteriaRepo criteriaRepo;

    @Override
    public Criteria create(Criteria criteria) {
        return this.criteriaRepo.save(
                requiredNotNull(criteria, EntityBadRequest::new)
        );
    }

    @Override
    public Criteria readById(Long id) {
        return this.criteriaRepo.findById(
                requiredNotNull(id, EntityBadRequest::new)
        ).orElseThrow(EntityNotFound::new);
    }

    @Override
    public List<Criteria> readAllByIds(List<Long> ids) {
        return this.criteriaRepo.findAllById(
                requiredColNullOrEmpty(ids, EntityBadRequest::new)
        );
    }

    @Override
    public void update(Criteria c) {
        this.criteriaRepo.save(
                requiredNotNull(c, EntityBadRequest::new)
        );
    }

    @Override
    public void updateStatus(Long id, EntityStatus status) {
        this.criteriaRepo.updateStatus(
                requiredNotNull(id, EntityBadRequest::new), status
        );
    }

}
