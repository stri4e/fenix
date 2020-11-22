package com.github.orders.service.impl;

import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.NotFound;
import com.github.orders.repository.OrderDetailRepo;
import com.github.orders.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Cacheable(value = "order", key = "#orderId")
    public OrderDetail readById(Long orderId) {
        return this.orderRepo.findById(orderId)
                .orElseThrow(NotFound::new);
    }

    @Override
    @Cacheable(value = "orders", key = "#userId", unless = "#result.getTotalElements() == 0")
    public Page<OrderDetail> readUserId(UUID userId, Pageable pageable) {
        return this.orderRepo.findByUserId(userId, pageable);
    }

    @Override
    @Cacheable(value = "orders", key = "#customerId", unless = "#result.getTotalElements() == 0")
    public Page<OrderDetail> readByCustomerId(Long customerId, Pageable pageable) {
        return this.orderRepo.findByCustomerId(customerId, pageable);
    }

    @Override
    @Cacheable(value = "orders", key = "#status", unless = "#result.getTotalElements() == 0")
    public Page<OrderDetail> readByStatus(OrderStatus status, Pageable pageable) {
        return this.orderRepo.findByStatus(status, pageable);
    }

    @Override
    public Page<OrderDetail> readByStaffIdNull(Pageable pageable) {
        return this.orderRepo.findByStaffIdNull(pageable);
    }

    @Override
    @Cacheable(value = "orders", key = "#staffId", unless = "#result.getTotalElements() == 0")
    public Page<OrderDetail> readByStaffIdAndStatus(Long staffId, OrderStatus status, Pageable pageable) {
        return this.orderRepo.findByStaffIdAndStatus(staffId, status, pageable);
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
            put = @CachePut(value = "order", key = "#orderId"),
            evict = @CacheEvict(value = "orders", allEntries = true)
    )
    public void updateOrderManager(Long orderId, Long staffId) {
        this.orderRepo.updateManagerOrder(orderId, staffId);
    }

}
