package com.github.orders.service.impl;

import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.NotFound;
import com.github.orders.repository.OrderDetailRepo;
import com.github.orders.service.IOrderDetailService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"order", "orders"})
public class OrderDetailService implements IOrderDetailService {

    private final OrderDetailRepo orderRepo;

    @Override
    @Caching(
            put = @CachePut(value = "order", key = "#o.id"),
            evict = @CacheEvict(value = "orders", allEntries = true)
    )
    public OrderDetail crete(OrderDetail o) {
         return this.orderRepo.save(o);
    }

    @Override
    public List<OrderDetail> read(OrderStatus status, LocalDateTime start, LocalDateTime end) {
        return this.orderRepo.findByStatusAndCreateAtBetween(status, start, end);
    }

    @Override
    @Cacheable(value = "order", key = "#orderId")
    public OrderDetail readById(Long orderId) {
        return this.orderRepo.findById(orderId)
                .orElseThrow(NotFound::new);
    }

    @Override
    @Cacheable(value = "orders", key = "#userId", unless = "#result.size() == 0")
    public List<OrderDetail> readUserId(UUID userId) {
        return this.orderRepo.findByUserId(userId);
    }

    @Override
    @Cacheable(value = "orders", key = "#status", unless = "#result.size() == 0")
    public List<OrderDetail> readByStatus(OrderStatus status) {
        return this.orderRepo.findByStatus(status);
    }

    @Override
    public List<OrderDetail> readByIds(List<Long> ids) {
        return Lists.newArrayList(this.orderRepo.findAllById(ids));
    }

    @Override
    @Caching(
            put = @CachePut(value = "order", key = "#id"),
            evict = @CacheEvict(value = "orders", allEntries = true)
    )
    public void update(Long id, OrderStatus status) {
        this.orderRepo.updateOrderByStatus(id, status);
    }

    @Override
    @Caching(
            put = @CachePut(value = "order", key = "#o.id"),
            evict = @CacheEvict(value = "orders", allEntries = true)
    )
    public void update(OrderDetail o) {
        this.orderRepo.save(o);
    }

    @Override
    public void updateOrderPaid(Long billId) {
        this.orderRepo.updateOrderPaid(billId, OrderStatus.paid);
    }

}
