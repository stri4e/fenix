package com.github.managers.controllers.impl;

import com.github.managers.controllers.IOrderDetailController;
import com.github.managers.dto.OrderDetailDto;
import com.github.managers.exceptions.NotFound;
import com.github.managers.services.IOrdersService;
import com.github.managers.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
