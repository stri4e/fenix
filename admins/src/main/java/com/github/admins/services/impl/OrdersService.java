package com.github.admins.services.impl;

import com.github.admins.dto.OrderDetailDto;
import com.github.admins.services.IOrdersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService implements IOrdersService {

    @Override
    public Optional<Page<OrderDetailDto>> readByStatus(String status, Pageable pageable) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderDetailDto> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Long productId, String orderStatus) {

    }
}
