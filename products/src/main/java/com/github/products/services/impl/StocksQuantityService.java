package com.github.products.services.impl;

import com.github.products.entity.StocksQuantity;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.repository.StocksQuantityRepo;
import com.github.products.services.IStocksQuantityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StocksQuantityService implements IStocksQuantityService {

    private final StocksQuantityRepo stocksQuantityRepo;

    @Override
    public void createAll(List<StocksQuantity> links) {
        this.stocksQuantityRepo.saveAll(links);
    }

    @Override
    public void create(StocksQuantity link) {
        this.stocksQuantityRepo.save(link);
    }

    @Override
    public List<StocksQuantity> readByProductId(Long productId) {
        return this.stocksQuantityRepo.findByProduct_Id(productId);
    }

    @Override
    public void updateQuantity(Long id, Integer quantity) {
        this.stocksQuantityRepo.updateQuantity(id, quantity);
    }

    @Override
    public void deleteById(Long id) {
        this.stocksQuantityRepo.deleteById(id);
    }

}
