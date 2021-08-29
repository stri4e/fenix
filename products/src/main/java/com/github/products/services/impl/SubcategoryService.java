package com.github.products.services.impl;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Subcategory;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.repository.SubcategoryRepo;
import com.github.products.services.ISubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubcategoryService implements ISubcategoryService {

    private final SubcategoryRepo subcategoryRepo;

    @Override
    public Subcategory create(Subcategory subCategory) {
        return this.subcategoryRepo.save(subCategory);
    }

    @Override
    public List<Subcategory> readAllByStatus(EntityStatus status) {
        return this.subcategoryRepo.findAllByStatus(status);
    }

    @Override
    public Subcategory readByName(String name) {
        return this.subcategoryRepo.findByName(name)
                .orElseThrow(EntityNotFound::new);
    }

    @Override
    public Subcategory readById(Long id) {
        return this.subcategoryRepo.findById(id)
                .orElseThrow(EntityNotFound::new);
    }

    @Override
    public Subcategory getById(Long id) {
        return this.subcategoryRepo.getOne(id);
    }

    @Override
    public void update(Subcategory subCategory) {
        this.subcategoryRepo.save(subCategory);
    }

    @Override
    public void updateSub(Subcategory subCategory) {
        this.subcategoryRepo.updateStatus(subCategory.getId(), subCategory.getName());
    }

    @Override
    public void delete(Long id) {
        this.subcategoryRepo.updateStatus(id, EntityStatus.off);
    }
}
