package com.github.products.services.impl;

import com.products.service.entity.Category;
import com.products.service.exceptions.BadRequest;
import com.products.service.repository.CategoryRepo;
import com.products.service.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements com.products.service.service.ICategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public List<Category> find() {
        return this.categoryRepo.findAll(Sort.by("id"));
    }

    @Override
    public Category create(Category c) {
        if (Objects.isNull(c)) {
            throw new BadRequest();
        }
        return this.categoryRepo.save(c);
    }

    @Override
    public Category findByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new BadRequest();
        }
        return this.categoryRepo.findByName(name);
    }

    @Override
    public void update(Category c) {
        this.categoryRepo.save(c);
    }

    @Override
    public void remove(Long id) {
        this.categoryRepo.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepo.findAll();
    }

}
