package com.github.products.services.impl;

import com.github.products.entity.Product;
import com.github.products.entity.ProductStatus;
import com.github.products.exceptions.BadRequest;
import com.github.products.exceptions.NotFound;
import com.github.products.exceptions.TypeMessage;
import com.github.products.repository.ProductRepo;
import com.github.products.services.IProductService;
import com.github.products.utils.ProductSpec;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepo productRepo;

    @Override
    public Page<Product> read(Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new BadRequest(TypeMessage.invalidPageable);
        }
        return this.productRepo.findAll(
                ProductSpec.statusUsed(), pageable
        );
    }

    @Override
    public Page<Product>
    readAllByCategory(String category, Pageable pageable) {
        if (StringUtils.isEmpty(category) || Objects.isNull(pageable)) {
            throw new BadRequest(TypeMessage.invalidPageableOrCategory);
        }
        return this.productRepo.findAll(
                ProductSpec.category(category), pageable
        );
    }

    @Override
    public Product create(Product p) {
        if (Objects.isNull(p)) {
            throw new BadRequest();
        }
        return this.productRepo.save(p);
    }

    @Override
    public Product readById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequest();
        }
        return this.productRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public List<Product> readAllUnPublish() {
        return this.productRepo
                .findAll(ProductSpec.statusUnUsed());
    }

    @Override
    public List<Product> readAllByIds(List<Long> ids) {
        return Lists.newArrayList(this.productRepo.findAllById(ids));
    }

    @Override
    public void updateProduct(Product p) {
        this.productRepo.save(p);
    }

    @Override
    public void updateStatus(ProductStatus status, Long id) {
        this.productRepo.updateStatus(status, id);
    }

}
