package com.github.products.services.impl;

import com.github.products.entity.Category;
import com.github.products.entity.EntityStatus;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.exceptions.ParametersBadRequest;
import com.github.products.repository.CategoryRepo;
import com.github.products.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.github.products.utils.NullSafety.*;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"category", "categories"})
public class CategoryService implements ICategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    @Cacheable(value = "categories", unless = "#result.size() == 0")
    public List<Category> readAllStatusOn() {
        return this.categoryRepo.findAllByStatus(EntityStatus.on, Sort.by("id"));
    }

    @Override
    @Caching(
            put = @CachePut(value = "category", key = "#c.id"),
            evict = @CacheEvict(value = "categories", allEntries = true)
    )
    public Category create(Category c) {
        return this.categoryRepo.save(
                requiredNotNull(c, EntityBadRequest::new)
        );
    }

    @Override
    @Cacheable(value = "category", key = "#name")
    public Category readByName(String name) {
        return this.categoryRepo.findByName(
                requiredNotBlank(name, ParametersBadRequest::new)
        ).orElseThrow(EntityNotFound::new);
    }

    @Override
    public Category getByName(String name) {
        return this.categoryRepo.getByName(
                requiredNotBlank(name, ParametersBadRequest::new)
        ).orElseThrow(EntityNotFound::new);
    }

    @Override
    public Category readById(Long id) {
        return this.categoryRepo.findById(
                requiredNotNull(id, ParametersBadRequest::new)
        ).orElseThrow(EntityNotFound::new);
    }

    @Override
    @Caching(
            put = @CachePut(value = "category", key = "#c.id"),
            evict = @CacheEvict(value = "categories", allEntries = true)
    )
    public void update(Category c) {
        throwIfNull(c, EntityBadRequest::new);
        this.categoryRepo.updateName(c.getId(), c.getName());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "category", key = "#id"),
            @CacheEvict(value = "categories", allEntries = true)
    })
    public void removeId(Long id) {
        this.categoryRepo.updateStatus(
                requiredNotNull(id, ParametersBadRequest::new),
                EntityStatus.off
        );
    }

    @Override
    @Cacheable(value = "categories", unless = "#result.size() == 0")
    public List<Category> readAll() {
        return this.categoryRepo.findAll();
    }

}
