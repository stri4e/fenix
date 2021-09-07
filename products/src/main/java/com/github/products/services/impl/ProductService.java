package com.github.products.services.impl;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Product;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.repository.ProductRepo;
import com.github.products.services.IProductService;
import com.github.products.utils.ProductSpec;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"product", "products", "products_by_subcategory", "products_unpublished"})
public class ProductService implements IProductService {

    private final ProductRepo productRepo;

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
                .orElseThrow(EntityNotFound::new);
    }

    @Override
    public Product getById(Long id) {
        return this.productRepo.getOne(id);
    }

    @Override
    @Cacheable(value = "products_unpublished", unless = "#result.size() == 0")
    public List<Product> readAllByStatusOff() {
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
    public void updateProduct(Product p) {
        this.productRepo.updateProduct(p);
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
    public void updateBoughtCountPlus(Long id) {
        this.productRepo.updateBoughCountPlus(id);
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
    public void updateBoughtCountMinus(Long id) {
        this.productRepo.updateBoughCountMinus(id);
    }

}
