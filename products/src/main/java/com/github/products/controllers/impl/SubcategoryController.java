package com.github.products.controllers.impl;

import com.github.products.controllers.ISubcategoryController;
import com.github.products.dto.SubcategoryDto;
import com.github.products.entity.EntityStatus;
import com.github.products.services.ICategoryService;
import com.github.products.services.ISubcategoryService;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static com.github.products.utils.TransferObj.fromSubCategory;
import static com.github.products.utils.TransferObj.toSubcategory;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/subcategories")
public class SubcategoryController implements ISubcategoryController {

    private final ISubcategoryService subcategoryService;

    private final ICategoryService categoryService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public SubcategoryDto save(String categoryName, SubcategoryDto payload) {
        return fromSubCategory(
                this.subcategoryService.create(categoryName, toSubcategory(payload)
                        .addCategory(this.categoryService.getByName(categoryName))
                )
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Object findByParams(String name, EntityStatus status) {
        if (StringUtils.hasText(name)) {
            return fromSubCategory(this.subcategoryService.readByName(name));
        } else {
            return this.subcategoryService.readAllByStatus(status).stream()
                    .map(TransferObj::fromSubCategory)
                    .collect(Collectors.toList());
        }
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(SubcategoryDto payload) {
        this.subcategoryService.update(toSubcategory(payload));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.subcategoryService.delete(id);
    }

}
