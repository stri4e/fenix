package com.github.products.services;

import com.github.products.entity.Criteria;
import com.github.products.entity.EntityStatus;

import java.util.List;

public interface ICriteriaService {

    Criteria create(Criteria criteria);

    Criteria readById(Long id);

    List<Criteria> readAllByIds(List<Long> ids);

    void update(Criteria criteria);

    void updateAll(List<Criteria> criteria);

    void updateStatus(Long id, EntityStatus status);

}
