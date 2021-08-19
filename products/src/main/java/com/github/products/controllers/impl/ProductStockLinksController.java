package com.github.products.controllers.impl;

import com.github.products.controllers.IProductStockLinksController;
import com.github.products.dto.ProductStockLinkDto;
import com.github.products.entity.Product;
import com.github.products.entity.ProductStockLink;
import com.github.products.entity.Stock;
import com.github.products.services.IProductService;
import com.github.products.services.IProductStockLinkService;
import com.github.products.services.IStocksService;
import com.github.products.utils.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Deprecated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/product/stock")
public class ProductStockLinksController implements IProductStockLinksController {

    private final IProductService productService;

    private final IStocksService stocksService;

    private final IProductStockLinkService productStockLinkService;

    @Override
    @Logging(isTime = true, isReturn = false)
    public void
    saveAllLinks(Long productId, @NotEmpty Map<Long, Integer> quantityGroupByStockId) {
        Product product = this.productService.getById(productId);
        List<Stock> stocks = this.stocksService.readAll(quantityGroupByStockId.keySet());
        List<ProductStockLink> links = stocks.stream()
                .map(stock -> new ProductStockLink(
                        product, stock, quantityGroupByStockId.get(stock.getId())
                )).collect(Collectors.toList());

        this.productStockLinkService.createAll(links);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void saveLink(@Valid ProductStockLinkDto payload) {
        Product product = this.productService.getById(payload.getProductId());
        Stock stock = this.stocksService.readById(payload.getStockId());
        this.productStockLinkService.create(
                new ProductStockLink(product, stock, payload.getQuantity())
        );
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void removeLink(Long linkId) {
        this.productStockLinkService.deleteById(linkId);
    }

}
