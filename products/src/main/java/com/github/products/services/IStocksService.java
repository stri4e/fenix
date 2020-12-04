package com.github.products.services;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.Stock;

import java.util.List;

public interface IStocksService {

    Stock create(Stock stock);

    List<Stock> readAll(EntityStatus status);

    Stock readById(Long id);

    Stock readByNameAndNumber(String name, String number);

    void update(Stock stock);

    void remove(Long id);

}
