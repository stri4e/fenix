package com.github.products.services;

import com.github.products.entity.StocksQuantity;

import java.util.List;

public interface IStocksQuantityService {

    void createAll(List<StocksQuantity> links);

    void create(StocksQuantity link);

    List<StocksQuantity> readByProductId(Long productId);

    void updateQuantity(Long id, Integer quantity);

    void deleteById(Long id);

}
