package com.github.products.services;

import com.github.products.entity.Criteria;
import com.github.products.entity.EntityStatus;

import java.util.List;

public interface ICriteriaService {

    Criteria create(Criteria criteria);

    Criteria readById(Long id);

    List<Criteria> readAll(List<Long> ids);

    void update(Criteria criteria);

    void updateStatus(Long id, EntityStatus status);

}
