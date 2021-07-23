package com.github.products.controllers.impl;

import com.github.products.controllers.IStocksController;
import com.github.products.dto.StockDto;
import com.github.products.entity.EntityStatus;
import com.github.products.entity.Stock;
import com.github.products.services.IStocksService;
import com.github.products.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.products.utils.TransferObj.fromStock;
import static com.github.products.utils.TransferObj.toStock;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/stocks")
public class StocksController implements IStocksController {

    private final IStocksService stocksService;

    @Override
    public StockDto save(@Valid StockDto payload) {
        return fromStock(this.stocksService.create(toStock(payload)));
    }

    @Override
    public List<StockDto> findByAll() {
        return this.stocksService.readAll(EntityStatus.on)
                .stream().map(TransferObj::fromStock)
                .collect(Collectors.toList());
    }

    @Override
    public void update(@Valid StockDto payload) {
        this.stocksService.updateStock(toStock(payload));
    }

    @Override
    public void remove(Long id) {
        this.stocksService.remove(id);
    }
}
