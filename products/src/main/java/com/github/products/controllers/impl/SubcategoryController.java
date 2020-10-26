package com.github.products.controllers.impl;

import com.github.products.controllers.ISubcategoryController;
import com.github.products.dto.SubcategoryDto;
import com.github.products.entity.Category;
import com.github.products.entity.EntityStatus;
import com.github.products.entity.Subcategory;
import com.github.products.services.ICategoryService;
import com.github.products.services.ISubcategoryService;
import com.github.products.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.products.utils.TransferObj.fromSubCategory;
import static com.github.products.utils.TransferObj.toSubCategory;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/subcategories")
public class SubcategoryController implements ISubcategoryController {

    private final ICategoryService categoryService;

    private final ISubcategoryService subCategoryService;

    @Override
    public SubcategoryDto save(String categoryName, SubcategoryDto payload) {
        Category category = this.categoryService.readByName(categoryName);
        Subcategory tmp = toSubCategory(payload).forCreate(category);
        Subcategory subcategory = this.subCategoryService.create(tmp);
        category.addSubcategory(subcategory);
        this.categoryService.update(category);
        return fromSubCategory(subcategory);
    }

    @Override
    public Object findByParams(String name, EntityStatus status) {
        if (StringUtils.hasText(name)) {
            return fromSubCategory(this.subCategoryService.readByName(name));
        } else {
            return this.subCategoryService.readAllByStatus(status).stream()
                    .map(TransferObj::fromSubCategory)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<SubcategoryDto> findAllByCategoryName(String categoryName) {
        return this.subCategoryService.readAllByCategoryName(categoryName).stream()
                .map(TransferObj::fromSubCategory)
                .collect(Collectors.toList());
    }

    @Override
    public void update(SubcategoryDto payload) {
        Subcategory subCategory = this.subCategoryService.readById(payload.getId());
        subCategory.setName(payload.getName());
        this.subCategoryService.update(subCategory);
    }

    @Override
    public void remove(Long id) {
        this.subCategoryService.delete(id);
    }
}
