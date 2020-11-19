package com.github.orders.controllers.impl;

import com.github.orders.controllers.IOrdersDetailController;
import com.github.orders.dto.*;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.NotFound;
import com.github.orders.service.*;
import com.github.orders.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.github.orders.payload.EmailNotification.registrationOrderNotify;
import static com.github.orders.utils.TransferObj.fromOrderDetail;
import static com.github.orders.utils.TransferObj.toOrderDetail;

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

    private final IEmailService emailService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public OrderDetailDto save(UUID userId, OrderDetailDto payload) {
        OrderDetail order = this.orderService.crete(toOrderDetail(payload, userId));
        OrderDetailDto result = payload.id(order.getId());
        CompletableFuture.runAsync(() -> this.ordersNotify.orderNotify(result));
        CompletableFuture.runAsync(() -> this.emailService.registrationOrderNotify(
                registrationOrderNotify(result)
        ));
        return result;
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<OrderDetailDto> findUserOrders(UUID userId, Pageable pageable) {
        Page<OrderDetail> orders = this.orderService.readUserId(userId, pageable);
        return new PageImpl<>(
                orders.getContent().stream()
                        .map(this::collect)
                        .collect(Collectors.toList()),
                pageable, orders.getTotalElements()
        );
    }

    @Override
    public Page<OrderDetailDto> findNewOrders(OrderStatus status, Pageable pageable) {
        Page<OrderDetail> orders = this.orderService.readByStatus(status, pageable);
        return new PageImpl<>(
                orders.getContent().stream()
                        .map(this::collect)
                        .collect(Collectors.toList()),
                pageable, orders.getTotalElements()
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public OrderDetailDto findById(Long id) {
        OrderDetail order = this.orderService.readById(id);
        List<ProductDto> products = this.productService.readByIds(order.getProductIds())
                .orElseThrow(NotFound::new);
        BillDto bill = this.billService.findById(order.getBillId())
                .orElseThrow(NotFound::new);
        CustomerDto customer = this.customerService.readById(order.getCustomerId())
                .orElseThrow(NotFound::new);
        DeliveryDto delivery = this.deliveryService.readById(order.getDeliveryId())
                .orElseThrow(NotFound::new);
        return fromOrderDetail(order, customer, delivery, products, bill);
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
    public void updateOrderPaid(Long billId) {
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
        CustomerDto customer = this.customerService.readById(order.getCustomerId())
                .orElseThrow(NotFound::new);
        DeliveryDto delivery = this.deliveryService.readById(order.getDeliveryId())
                .orElseThrow(NotFound::new);
        BillDto bill = this.billService.findById(order.getBillId())
                .orElseThrow(NotFound::new);
        return fromOrderDetail(order, customer, delivery, products, bill);
    }

}
