package com.github.products.services.impl;

import com.github.products.entity.ProductStockLink;
import com.github.products.repository.ProductStockLinkRepo;
import com.github.products.services.IProductStockLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductStockLinkService implements IProductStockLinkService {

    private final ProductStockLinkRepo productStockLinkRepo;

    @Override
    public void createAll(List<ProductStockLink> links) {
        this.productStockLinkRepo.saveAll(links);
    }

    @Override
    public void create(ProductStockLink link) {
        this.productStockLinkRepo.save(link);
    }

    @Override
    public void deleteById(Long id) {
        this.productStockLinkRepo.deleteById(id);
    }

}
