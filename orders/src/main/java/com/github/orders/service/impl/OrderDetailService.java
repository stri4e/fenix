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

import static com.github.orders.entity.OrderStatus.done;
import static com.github.orders.entity.OrderStatus.returned;

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
    public Long readCustomerIdByOrderId(Long orderId) {
        return this.orderRepo.findCustomerIdByOrderId(orderId)
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
    public Integer countTotalByCustomerId(Long customerId) {
        return this.orderRepo.countAllByCustomerId(customerId)
                .orElse(0);
    }

    @Override
    public Integer countSuccessByCustomerId(Long customerId) {
        return this.orderRepo.countAllByCustomerIdAndStatus(customerId, done)
                .orElse(0);
    }

    @Override
    public Integer countReturnedByCustomerId(Long customerId) {
        return this.orderRepo.countAllByCustomerIdAndStatus(customerId, returned)
                .orElse(0);
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
