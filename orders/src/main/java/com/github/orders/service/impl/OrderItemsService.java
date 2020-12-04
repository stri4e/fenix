package com.github.orders.service.impl;

import com.github.orders.entity.OrderItem;
import com.github.orders.repository.OrderItemsRepo;
import com.github.orders.service.IOrderItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemsService implements IOrderItemsService {

    private final OrderItemsRepo orderItemsRepo;

    @Override
    public List<OrderItem> createAll(List<OrderItem> items) {
        return this.orderItemsRepo.saveAll(items);
    }
    
}
