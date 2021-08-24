package com.github.products.controllers.impl;

import com.github.products.controllers.IStocksQuantityController;
import com.github.products.dto.StocksQuantityDto;
import com.github.products.entity.Product;
import com.github.products.entity.StocksQuantity;
import com.github.products.entity.Stock;
import com.github.products.services.IProductService;
import com.github.products.services.IStocksQuantityService;
import com.github.products.services.IStocksService;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/product/stock/quantity")
public class StockQuantityController implements IStocksQuantityController {

    private final IProductService productService;

    private final IStocksService stocksService;

    private final IStocksQuantityService stocksQuantityService;

    @Override
    @Logging(isTime = true, isReturn = false)
    public void saveAll(Long productId, @NotEmpty List<StocksQuantityDto> payload) {
        Map<Long, Stock> stocks = this.stocksService.readAll(
                payload.stream()
                        .map(link -> link.getStock().getId())
                        .collect(Collectors.toSet())
        ).stream().collect(Collectors.toMap(Stock::getId, Function.identity()));
        Product product = this.productService.getById(productId);
        this.stocksQuantityService.createAll(
                payload.stream()
                        .map(l -> new StocksQuantity(
                                product,
                                stocks.get(l.getStock().getId()),
                                l.getQuantity())
                        ).collect(Collectors.toList())
        );
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void save(Long productId, @Valid StocksQuantityDto payload) {
        Product product = this.productService.getById(productId);
        Stock stock = this.stocksService.readById(payload.getStock().getId());
        this.stocksQuantityService.create(
                new StocksQuantity(product, stock, payload.getQuantity())
        );
    }

    @Override
    public List<StocksQuantityDto> findByProductId(Long productId) {
        return this.stocksQuantityService.readByProductId(productId).stream()
                .map(TransferObj::fromStocksQuantity)
                .collect(Collectors.toList());
    }

    @Override
    public void updateQuantity(Long id, Integer quantity) {
        this.stocksQuantityService.updateQuantity(id, quantity);
    }


    @Override
    @Logging(isTime = true, isReturn = false)
    public void remove(Long linkId) {
        this.stocksQuantityService.deleteById(linkId);
    }

}
