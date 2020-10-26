package com.github.products.services.impl;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Specification;
import com.github.products.exceptions.BadRequest;
import com.github.products.exceptions.NotFound;
import com.github.products.repository.SpecificationRepo;
import com.github.products.services.ISpecificationService;
import com.github.products.utils.SpecificationSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
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
            throw new BadRequest();
        }
        return this.specificationRepo.save(s);
    }

    @Override
    @Cacheable(value = "specification", key = "#id")
    public Specification readById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequest();
        }
        return this.specificationRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    @Caching(
            put = @CachePut(value = "specification", key = "#s.id")
    )
    public void update(Specification s) {
        this.specificationRepo.save(s);
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
