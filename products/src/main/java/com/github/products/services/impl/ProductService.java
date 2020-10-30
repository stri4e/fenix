package com.github.products.services.impl;

import com.github.products.entity.Criteria;
import com.github.products.entity.EntityStatus;
import com.github.products.entity.Product;
import com.github.products.exceptions.BadRequest;
import com.github.products.exceptions.NotFound;
import com.github.products.repository.ProductRepo;
import com.github.products.services.IProductService;
import com.github.products.utils.ProductSpec;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static com.github.products.utils.ProductSpec.selectCriteriaIn;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"product", "products", "products_by_subcategory", "products_unpublished"})
public class ProductService implements IProductService {

    private final ProductRepo productRepo;

    @Override
    @Cacheable(value = "products", unless = "#result.size() == 0")
    public Page<Product> read(Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new BadRequest();
        }
        return this.productRepo.findAll(
                ProductSpec.statusUsed(), pageable
        );
    }

    @Override
    @Cacheable(value = "products_by_subcategory", key = "#category")
    public Page<Product>
    readAllByCategory(String subcategory, Pageable pageable) {
        if (StringUtils.isEmpty(subcategory) || Objects.isNull(pageable)) {
            throw new BadRequest();
        }
        return this.productRepo.findAll(
                ProductSpec.subcategory(subcategory), pageable
        );
    }

    @Override
    public Page<Product>
    readByParams(String subcategoryName, List<Criteria> criteria, Pageable pageable) {
        return this.productRepo.findAll(selectCriteriaIn(subcategoryName, criteria), pageable);
    }

    @Override
    public List<Product> searchProduct(String name, String description) {
        if (StringUtils.isEmpty(name)) {
            throw new BadRequest();
        }
        return this.productRepo.findByNameContainingOrDescriptionContaining(
                name, description
        );
    }

    @Override
    @Caching(
            put = @CachePut(value = "product", key = "#p.id"),
            evict = {
                    @CacheEvict(value = "products", allEntries = true),
                    @CacheEvict(value = "products_unpublished", allEntries = true),
                    @CacheEvict(value = "products_by_subcategory", allEntries = true),
            }
    )
    public Product create(Product p) {
        if (Objects.isNull(p)) {
            throw new BadRequest();
        }
        return this.productRepo.save(p);
    }

    @Override
    @Cacheable(value = "product", key = "#id")
    public Product readById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequest();
        }
        return this.productRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    @Cacheable(value = "products_unpublished", unless = "#result.size() == 0")
    public List<Product> readAllUnPublish() {
        return this.productRepo
                .findAll(ProductSpec.statusUnUsed());
    }

    @Override
    public List<Product> readAllByIds(List<Long> ids) {
        return Lists.newArrayList(this.productRepo.findAllById(ids));
    }

    @Override
    @Caching(
            put = @CachePut(value = "product", key = "#p.id"),
            evict = {
                    @CacheEvict(value = "products", allEntries = true),
                    @CacheEvict(value = "products_unpublished", allEntries = true),
                    @CacheEvict(value = "products_by_subcategory", allEntries = true),
            }
    )
    public void update(Product p) {
        this.productRepo.save(p);
    }

    @Override
    @Caching(
            put = @CachePut(value = "product", key = "#id"),
            evict = {
                    @CacheEvict(value = "products", allEntries = true),
                    @CacheEvict(value = "products_unpublished", allEntries = true),
                    @CacheEvict(value = "products_by_subcategory", allEntries = true),
            }
    )
    public void updateStatus(EntityStatus status, Long id) {
        this.productRepo.updateStatus(status, id);
    }

    @Override
    @Caching(
            put = @CachePut(value = "product", key = "#id"),
            evict = {
                    @CacheEvict(value = "products", allEntries = true),
                    @CacheEvict(value = "products_unpublished", allEntries = true),
                    @CacheEvict(value = "products_by_subcategory", allEntries = true),
            }
    )
    public void updatePercentBought(Long id, Double percentBought) {
        this.productRepo.updatePercentBough(id, percentBought);
    }

}
