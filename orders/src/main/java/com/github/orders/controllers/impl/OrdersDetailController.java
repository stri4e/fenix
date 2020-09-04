package com.github.orders.controllers.impl;

import com.github.orders.controllers.IOrdersDetailController;
import com.github.orders.dto.OrderDetailDto;
import com.github.orders.dto.OrderDto;
import com.github.orders.entity.Customer;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.NotFound;
import com.github.orders.payload.Product;
import com.github.orders.service.ICustomerService;
import com.github.orders.service.IOrderDetailService;
import com.github.orders.service.IProductService;
import com.github.orders.service.IPushOrders;
import com.github.orders.utils.Logging;
import com.github.orders.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.github.orders.utils.TransferObj.toCustomer;
import static com.github.orders.utils.TransferObj.toOrderDetail;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class OrdersDetailController implements IOrdersDetailController {

    private final ICustomerService customerService;

    private final IOrderDetailService orderService;

    private final IProductService productService;

    private final IPushOrders pushOrders;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void saveOrders(Long userId, OrderDetailDto payload) {
        Customer customer = this.customerService.create(toCustomer(payload.getCustomer()));
        OrderDetail order = this.orderService.crete(toOrderDetail(customer, payload, userId));
        List<Product> products = this.productService.readByIds(order.getProductIds())
                .orElseThrow(NotFound::new);
        OrderDto pushData = TransferObj.fromOrderDetailDto(order, products);
        this.pushOrders.pushOrder(pushData);
    }

    @Override
    public List<OrderDto>
    fetchOrdersInTime(OrderStatus status, LocalDateTime start, LocalDateTime end) {
        List<OrderDetail> orders = this.orderService.read(status, start, end);
        return orders.stream()
                .map(this::collect)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> userOrders(Long userId) {
        List<OrderDetail> orders = this.orderService.readUserId(userId);
        return orders.stream()
                .map(this::collect)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> fetchBindingOrders(Long orderId) {
        OrderDetail order = this.orderService.readById(orderId);
        List<OrderDetail> orders = this.orderService.readUserId(order.getUserId());
        return orders.stream()
                .map(this::collect)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<OrderDto> findAllByStatus(OrderStatus status) {
        List<OrderDetail> orders = this.orderService.readByStatus(status);
        return orders.stream()
                .map(this::collect)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Object findByParams(Long id, List<Long> ids) {
        if (Objects.nonNull(id)) {
            return this.orderService.readById(id);
        } else {
            List<OrderDetail> orders = this.orderService.readByIds(ids);
            return orders.stream()
                    .map(this::collect)
                    .collect(Collectors.toList());
        }
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateOrder(OrderDetail payload) {
        this.orderService.update(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateOderStatus(Long orderId, OrderStatus status) {
        this.orderService.update(orderId, status);
    }

    private OrderDto collect(OrderDetail order) {
        List<Product> pr = this.productService.readByIds(order.getProductIds())
                .orElseThrow(NotFound::new);
        return TransferObj.fromOrderDetailDto(
                order,
                pr
        );
    }

}
