package com.github.products.controllers.impl;

import com.github.products.controllers.ISubCategoryController;
import com.github.products.dto.SubCategoryDto;
import com.github.products.entity.Category;
import com.github.products.entity.EntityStatus;
import com.github.products.entity.SubCategory;
import com.github.products.services.ICategoryService;
import com.github.products.services.ISubCategoryService;
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
@RequestMapping(path = "/v1/sub/categories")
public class SubCategoryController implements ISubCategoryController {

    private final ICategoryService categoryService;

    private final ISubCategoryService subCategoryService;

    @Override
    public SubCategoryDto save(String categoryName, SubCategoryDto payload) {
        Category category = this.categoryService.readByName(categoryName);
        SubCategory tmp = toSubCategory(payload).forCreate(category);
        return fromSubCategory(this.subCategoryService.create(tmp));
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
    public List<SubCategoryDto> findAllByCategoryName(String categoryName) {
        return this.subCategoryService.readAllByCategoryName(categoryName).stream()
                .map(TransferObj::fromSubCategory)
                .collect(Collectors.toList());
    }

    @Override
    public void update(SubCategoryDto payload) {
        SubCategory subCategory = this.subCategoryService.readById(payload.getId());
        subCategory.setName(payload.getName());
        this.subCategoryService.update(subCategory);
    }

    @Override
    public void remove(Long id) {
        this.subCategoryService.delete(id);
    }
}
