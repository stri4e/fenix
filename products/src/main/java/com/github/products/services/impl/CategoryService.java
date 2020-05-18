package com.github.products.services.impl;

import com.github.products.entity.Category;
import com.github.products.exceptions.BadRequest;
import com.github.products.repository.CategoryRepo;
import com.github.products.services.ICategoryService;
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
public class CategoryService implements ICategoryService {

    private final CategoryRepo categoryRepo;

    public List<Category> read() {
        return this.categoryRepo.findAll(Sort.by("id"));
    }

    @Override
    public Category create(Category c) {
        if (Objects.isNull(c)) {
            throw new BadRequest();
        }
        return this.categoryRepo.save(c);
    }

    public Category readByName(String name) {
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

    public List<Category> readAll() {
        return this.categoryRepo.findAll();
    }

}
