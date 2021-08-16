package com.github.products.services;

import com.github.products.entity.ProductStockLink;

import java.util.List;

public interface IProductStockLinkService {

    void createAll(List<ProductStockLink> links);

}
