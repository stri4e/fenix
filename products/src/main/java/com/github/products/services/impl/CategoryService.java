package com.github.products.services.impl;

import com.github.products.entity.Category;
import com.github.products.entity.EntityStatus;
import com.github.products.exceptions.BadRequest;
import com.github.products.exceptions.NotFound;
import com.github.products.repository.CategoryRepo;
import com.github.products.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"category", "categories"})
public class CategoryService implements ICategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    @Cacheable(value = "categories", unless = "#result.size() == 0")
    public List<Category> read() {
        return this.categoryRepo.findAll(Sort.by("id"));
    }

    @Override
    @Caching(
            put = @CachePut(value = "category", key = "#c.id"),
            evict = @CacheEvict(value = "categories", allEntries = true)
    )
    public Category create(Category c) {
        if (Objects.isNull(c)) {
            throw new BadRequest();
        }
        return this.categoryRepo.save(c);
    }

    @Override
    @Cacheable(value = "category", key = "#name")
    public Category readByName(String name) {
        return this.categoryRepo.findByName(name);
    }

    @Override
    public Category readById(Long id) {
        return this.categoryRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    @Caching(
            put = @CachePut(value = "category", key = "#c.id"),
            evict = @CacheEvict(value = "categories", allEntries = true)
    )
    public void update(Category c) {
        this.categoryRepo.save(c);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "category", key = "#id"),
            @CacheEvict(value = "categories", allEntries = true)
    })
    public void remove(Long id) {
        this.categoryRepo.updateStatus(id, EntityStatus.off);
    }

    @Override
    @Cacheable(value = "categories", unless = "#result.size() == 0")
    public List<Category> readAll() {
        return this.categoryRepo.findAll();
    }

}
