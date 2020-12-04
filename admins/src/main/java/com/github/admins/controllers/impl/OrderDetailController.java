package com.github.admins.controllers.impl;

import com.github.admins.controllers.IOrderDetailController;
import com.github.admins.dto.OrderDetailDto;
import com.github.admins.exceptions.NotFound;
import com.github.admins.services.IOrdersService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/v1/orders")
@RequiredArgsConstructor
public class OrderDetailController implements IOrderDetailController {

    private final IOrdersService orderService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<OrderDetailDto> findByStatus(String status, Pageable pageable) {
        return this.orderService.readByStatus(status, pageable)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public OrderDetailDto findById(Long orderId) {
        return this.orderService.readById(orderId)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateOrderStatus(Long orderId, String orderStatus) {
        this.orderService.update(orderId, orderStatus);
    }

}
