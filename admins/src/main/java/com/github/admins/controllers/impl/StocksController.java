package com.github.admins.controllers.impl;

import com.github.admins.controllers.IStocksController;
import com.github.admins.dto.StockDto;
import com.github.admins.services.IStocksService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/stocks")
public class StocksController implements IStocksController {

    private final IStocksService stocksService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public StockDto save(@Valid StockDto payload) {
        return this.stocksService.save(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<StockDto> findByAll() {
        return this.stocksService.findByAll();
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(@Valid StockDto payload) {
        this.stocksService.update(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.stocksService.remove(id);
    }
}
