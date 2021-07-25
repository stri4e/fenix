package com.github.products.services;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Stock;

import java.util.List;
import java.util.Set;

public interface IStocksService {

    Stock create(Stock stock);

    List<Stock> readAll(EntityStatus status);

    List<Stock> readAll(Set<Long> ids);

    Stock readById(Long id);

    Stock readByNameAndNumber(String name, String number);

    void update(Stock stock);

    void updateStock(Stock stock);

    void remove(Long id);

}
