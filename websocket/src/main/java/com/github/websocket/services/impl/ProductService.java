package com.github.websocket.services.impl;

import com.github.websocket.payload.Product;
import com.github.websocket.services.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Override
    public List<Product> readByIds(List<Long> ids) {
        return null;
    }
}
