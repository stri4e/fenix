package com.github.orders.controllers.impl;

import com.github.orders.controllers.IOrdersDetailController;
import com.github.orders.dto.BillDto;
import com.github.orders.dto.OrderDetailDto;
import com.github.orders.dto.ProductDto;
import com.github.orders.entity.Customer;
import com.github.orders.entity.Delivery;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.NotFound;
import com.github.orders.service.*;
import com.github.orders.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.github.orders.utils.TransferObj.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class OrdersDetailController implements IOrdersDetailController {

    private final ICustomerService customerService;

    private final IOrderDetailService orderService;

    private final IProductService productService;

    private final IOrdersNotify ordersNotify;

    private final IDeliveryService deliveryService;

    private final IBillService billService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void save(Long userId, OrderDetailDto payload) {
        Customer customer = this.customerService.create(toCustomer(payload.getCustomer()));
        Delivery delivery = this.deliveryService.create(toDelivery(payload.getDelivery()));
        BillDto bill = this.billService.create(payload.getBill());
        OrderDetail tmp = toOrderDetail(customer, payload, delivery, userId, bill.getId());
        OrderDetail order = this.orderService.crete(tmp);
        CompletableFuture.runAsync(() -> this.ordersNotify.orderNotify(
                fromOrderDetail(order, payload.getProducts(), bill))
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<OrderDetailDto>
    fetchOrdersInTime(OrderStatus status, LocalDateTime start, LocalDateTime end) {
        List<OrderDetail> orders = this.orderService.read(status, start, end);
        return orders.stream()
                .map(this::collect)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<OrderDetailDto> userOrders(Long userId) {
        List<OrderDetail> orders = this.orderService.readUserId(userId);
        return orders.stream()
                .map(this::collect)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<OrderDetailDto> fetchBindingOrders(Long orderId) {
        OrderDetail order = this.orderService.readById(orderId);
        List<OrderDetail> orders = this.orderService.readUserId(order.getUserId());
        return orders.stream()
                .map(this::collect)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<OrderDetailDto> findAllByStatus(OrderStatus status) {
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
            OrderDetail order = this.orderService.readById(id);
            List<ProductDto> products = this.productService.readByIds(order.getProductIds())
                    .orElseThrow(NotFound::new);
            BillDto bill = this.billService.findById(order.getBillId());
            return fromOrderDetail(order, products, bill);
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

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateOderPaid(Long billId) {
        this.orderService.updateOrderPaid(billId);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        OrderDetail order = this.orderService.readById(id);
        this.billService.remove(order.getBillId());
        this.orderService.update(id, OrderStatus.canceling);
    }

    private OrderDetailDto collect(OrderDetail order) {
        List<ProductDto> products = this.productService.readByIds(order.getProductIds())
                .orElseThrow(NotFound::new);
        BillDto bill = this.billService.findById(order.getBillId());
        return fromOrderDetail(order, products, bill);
    }

}
