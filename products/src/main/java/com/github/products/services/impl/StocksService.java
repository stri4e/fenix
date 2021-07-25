package com.github.products.services.impl;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Stock;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.repository.StocksRepo;
import com.github.products.services.IStocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class StocksService implements IStocksService {

    private final StocksRepo stocksRepo;

    @Override
    public Stock create(Stock stock) {
        return this.stocksRepo.save(stock);
    }

    @Override
    public List<Stock> readAll(EntityStatus status) {
        return this.stocksRepo.findByStatus(status);
    }

    @Override
    public List<Stock> readAll(Set<Long> ids) {
        return this.stocksRepo.findAllById(ids);
    }

    @Override
    public Stock readById(Long id) {
        return this.stocksRepo.findById(id)
                .orElseThrow(EntityNotFound::new);
    }

    @Override
    public Stock readByNameAndNumber(String name, String number) {
        return this.stocksRepo.findByNameAndNumber(name, number)
                .orElseThrow(EntityNotFound::new);
    }

    @Override
    public void update(Stock stock) {
        this.stocksRepo.save(stock);
    }

    @Override
    public void updateStock(Stock stock) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Long id) {
        this.stocksRepo.update(id, EntityStatus.off);
    }

}
