package com.github.products.services.impl;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Subcategory;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.repository.CategoryRepo;
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

    private final CategoryRepo categoryRepo;

    @Override
    public Subcategory create(String categoryName, Subcategory subCategory) {
        return this.subCategoryRepo.save(subCategory.addCategory(
                this.categoryRepo.getByName(categoryName)
                        .orElseThrow(EntityNotFound::new)
        ));
    }

    @Override
    public List<Subcategory> readAllByStatus(EntityStatus status) {
        return this.subCategoryRepo.findAllByStatus(status);
    }

    @Override
    public Subcategory readByName(String name) {
        return this.subCategoryRepo.findByName(name)
                .orElseThrow(EntityNotFound::new);
    }

    @Override
    public Subcategory readById(Long id) {
        return this.subCategoryRepo.findById(id)
                .orElseThrow(EntityNotFound::new);
    }

    @Override
    public void update(Subcategory subCategory) {
        this.subCategoryRepo.save(subCategory);
    }

    @Override
    public void updateSub(Subcategory subCategory) {
        this.subCategoryRepo.updateStatus(subCategory.getId(), subCategory.getName());
    }

    @Override
    public void delete(Long id) {
        this.subCategoryRepo.updateStatus(id, EntityStatus.off);
    }
}
