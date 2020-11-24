package com.github.products.services.impl;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Stock;
import com.github.products.exceptions.NotFound;
import com.github.products.repository.StocksRepo;
import com.github.products.services.IStocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public Stock readById(Long id) {
        return this.stocksRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Stock readByNameAndNumber(String name, String number) {
        return this.stocksRepo.findByNameAndNumber(name, number)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Stock stock) {
        this.stocksRepo.save(stock);
    }

    @Override
    public void remove(Long id) {
        this.stocksRepo.update(id, EntityStatus.off);
    }

}
