package com.github.products.services.impl;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Specification;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.repository.SpecificationRepo;
import com.github.products.services.ISpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static com.github.products.utils.SpecificationSpec.selectBy;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"specification"})
public class SpecificationService implements ISpecificationService {

    private final SpecificationRepo specificationRepo;

    @Override
    @Caching(
            put = @CachePut(value = "specification", key = "#s.id")
    )
    public Specification create(Specification s) {
        if (Objects.isNull(s)) {
            throw new EntityBadRequest();
        }
        return this.specificationRepo.save(s);
    }

    @Override
    @Cacheable(value = "specification", key = "#id")
    public Specification readById(Long id) {
        if (Objects.isNull(id)) {
            throw new EntityBadRequest();
        }
        return this.specificationRepo.findById(id)
                .orElseThrow(EntityNotFound::new);
    }

    @Override
    public List<Specification> readAllById(List<Long> ids) {
        return this.specificationRepo.findAllById(ids);
    }

    @Override
    @Caching(
            put = @CachePut(value = "specification", key = "#s.id")
    )
    public void update(Specification s) {
        this.specificationRepo.save(s);
    }

    @Override
    public void updateSpec(Specification s) {
        this.specificationRepo.updateSpec(s);
    }

    @Override
    @Caching(
            put = @CachePut(value = "specification", key = "#id")
    )
    public void delete(Long id) {
        this.specificationRepo.update(id, EntityStatus.off);
    }

    @Override
    public List<Specification> readByParams(String name, String patter) {
        return this.specificationRepo.findAll(selectBy(name, patter));
    }

}
