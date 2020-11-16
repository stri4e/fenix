package com.github.products.services.impl;

import com.github.products.entity.Criteria;
import com.github.products.entity.EntityStatus;
import com.github.products.entity.Product;
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

import javax.transaction.Transactional;
import java.util.List;

import static com.github.products.utils.ProductSpec.*;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"product", "products", "products_by_subcategory", "products_unpublished"})
public class ProductService implements IProductService {

    private final ProductRepo productRepo;

    @Override
    @Cacheable(value = "products", unless = "#result.getTotalElements() == 0")
    public Page<Product> read(Pageable pageable) {
        return this.productRepo.findAll(statusOn(), pageable);
    }

    @Override
    @Cacheable(value = "products_by_subcategory", key = "#subcategory")
    public Page<Product>
    readAllBySubcategory(String subcategory, Pageable pageable) {
        return this.productRepo.findAll(bySubcategory(subcategory), pageable);
    }

    @Override
    public Page<Product>
    readByParams(String subcategoryName, List<Criteria> criteria, Pageable pageable) {
        return this.productRepo.findAll(selectCriteriaIn(subcategoryName, criteria), pageable);
    }

    @Override
    public List<Product> searchProduct(String name, String description) {
        return this.productRepo.findByNameContainingOrDescriptionContaining(name, description);
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
        return this.productRepo.save(p);
    }

    @Override
    @Cacheable(value = "product", key = "#id")
    public Product readById(Long id) {
        return this.productRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    @Cacheable(value = "products_unpublished", unless = "#result.size() == 0")
    public List<Product> readAllOff() {
        return this.productRepo
                .findAll(ProductSpec.statusOff());
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
    public void updateBoughtCount(Long id, Integer percentBought) {
        this.productRepo.updateBoughCount(id, percentBought);
    }

}
