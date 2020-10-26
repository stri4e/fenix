package com.github.products.services.impl;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Subcategory;
import com.github.products.exceptions.NotFound;
import com.github.products.repository.SubcategoryRepo;
import com.github.products.services.ISubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubcategoryService implements ISubcategoryService {

    private final SubcategoryRepo subCategoryRepo;

    @Override
    public Subcategory create(Subcategory subCategory) {
        return this.subCategoryRepo.save(subCategory);
    }

    @Override
    public List<Subcategory> readAllByStatus(EntityStatus status) {
        return this.subCategoryRepo.findAllByStatus(status);
    }

    @Override
    public Subcategory readByName(String name) {
        return this.subCategoryRepo.findByName(name)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Subcategory readById(Long id) {
        return this.subCategoryRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public List<Subcategory> readAllByCategoryName(String categoryName) {
        return this.subCategoryRepo.findAllByStatusAndCategoryName(
                EntityStatus.on, categoryName
        );
    }

    @Override
    public void update(Subcategory subCategory) {
        this.subCategoryRepo.save(subCategory);
    }

    @Override
    public void delete(Long id) {
        this.subCategoryRepo.updateStatus(id, EntityStatus.off);
    }
}
