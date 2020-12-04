package com.github.admins.services.impl;

import com.github.admins.dto.StockDto;
import com.github.admins.services.IStocksService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class StocksService implements IStocksService {

    @Override
    public StockDto save(@Valid StockDto payload) {
        return null;
    }

    @Override
    public List<StockDto> findByAll() {
        return null;
    }

    @Override
    public void update(@Valid StockDto payload) {

    }

    @Override
    public void remove(Long id) {

    }
}
