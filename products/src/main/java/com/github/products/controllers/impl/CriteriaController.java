package com.github.products.controllers.impl;

import com.github.products.controllers.ICriteriaController;
import com.github.products.dto.CriteriaDto;
import com.github.products.entity.Criteria;
import com.github.products.entity.Filter;
import com.github.products.entity.Product;
import com.github.products.services.ICriteriaService;
import com.github.products.services.IFiltersService;
import com.github.products.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.products.entity.EntityStatus.off;
import static com.github.products.utils.TransferObj.fromCriteria;
import static com.github.products.utils.TransferObj.toCriteria;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/criteria")
public class CriteriaController implements ICriteriaController {

    private final IFiltersService filtersService;

    private final ICriteriaService criteriaService;

    private final IProductService productService;

    @Override
    public CriteriaDto saveToFilters(Long filterId, CriteriaDto payload) {
        Filter filter = this.filtersService.readById(filterId);
        Criteria criteria = this.criteriaService.create(toCriteria(payload));
        filter.addCriteria(criteria);
        this.filtersService.update(filter);
        return fromCriteria(criteria);
    }

    @Override
    public void saveToProducts(Long productId, List<Long> payload) {
        Product product = this.productService.readById(productId);
        List<Criteria> criteria = this.criteriaService.readAll(payload);
        product.addCriteria(criteria);
        this.productService.update(product);
    }

    @Override
    public CriteriaDto findById(Long id) {
        return fromCriteria(this.criteriaService.readById(id));
    }

    @Override
    public void update(CriteriaDto payload) {
        Criteria criteria = this.criteriaService.readById(payload.getId());
        criteria.setValue(payload.getValue());
        this.criteriaService.update(criteria);
    }

    @Override
    public void updateInProducts(Long productId, Long criteriaId) {
        Product product = this.productService.readById(productId);
        Criteria criteria = this.criteriaService.readById(criteriaId);
        product.addCriteria(criteria);
        this.productService.update(product);
    }

    @Override
    public void removeInProducts(Long productId, Long criteriaId) {
        Product product = this.productService.readById(productId);
        Criteria criteria = this.criteriaService.readById(criteriaId);
        product.getCriteria().remove(criteria);
        this.productService.update(product);
    }

    @Override
    public void updateInFilters(Long filterId, Long criteriaId) {
        Filter filter = this.filtersService.readById(filterId);
        Criteria criteria = this.criteriaService.readById(criteriaId);
        filter.addCriteria(criteria);
        this.filtersService.update(filter);
    }

    @Override
    public void removeInFilters(Long filterId, Long criteriaId) {
        Filter filter = this.filtersService.readById(filterId);
        Criteria criteria = this.criteriaService.readById(criteriaId);
        filter.getCriteria().remove(criteria);
        this.filtersService.update(filter);
    }

    @Override
    public void remove(Long id) {
        this.criteriaService.updateStatus(id, off);
    }
}
