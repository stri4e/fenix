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
import java.util.stream.Collectors;

import static com.github.orders.payload.EmailNotification.registrationOrderNotify;
import static com.github.orders.utils.TransferObj.fromOrderDetail;
import static com.github.orders.utils.TransferObj.toOrderDetail;
import static java.util.concurrent.CompletableFuture.runAsync;

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
        runAsync(() -> this.ordersNotify.orderNotify(result));
        runAsync(() -> this.emailService.registrationOrderNotify(
                registrationOrderNotify(result)
        ));
        return result;
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<OrderDetailDto> findUserOrders(UUID userId, Pageable pageable) {
        return toOrderPage(this.orderService.readUserId(userId, pageable), pageable);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<OrderDetailDto> findNewOrders(OrderStatus status, Pageable pageable) {
        return toOrderPage(this.orderService.readByStatus(status, pageable), pageable);
    }

    @Override
    public Page<OrderDetailDto> findManagerOrders(OrderStatus status, UUID managerId, Pageable pageable) {
        return toOrderPage(this.orderService.readByManagerIdAndStatus(managerId, status, pageable), pageable);
    }

    @Override
    public Page<OrderDetailDto> unassignedOrders(Pageable pageable) {
        return toOrderPage(this.orderService.readByManagerIdNull(pageable), pageable);
    }

    private Page<OrderDetailDto> toOrderPage(Page<OrderDetail> orders, Pageable pageable) {
        List<OrderDetail> content = orders.getContent();
        OrderDetail order = content.stream().findFirst().orElseThrow(NotFound::new);
        CustomerDto customer = this.customerService.readById(order.getCustomerId())
                .orElseThrow(NotFound::new);
        DeliveryDto delivery = this.deliveryService.readById(order.getDeliveryId())
                .orElseThrow(NotFound::new);
        return new PageImpl<>(
                orders.getContent().stream()
                        .map(o -> collect(o, customer, delivery))
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
    public void updateStatus(Long orderId, OrderStatus status) {
        this.orderService.update(orderId, status);
    }

    @Override
    public void assignManager(Long orderId, UUID managerID) {
        this.orderService.updateOrderManager(orderId, managerID);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.orderService.update(id, OrderStatus.canceling);
    }

    private OrderDetailDto collect(OrderDetail order, CustomerDto customer, DeliveryDto delivery) {
        List<ProductDto> products = this.productService.readByIds(order.getProductIds())
                .orElseThrow(NotFound::new);
        BillDto bill = this.billService.findById(order.getBillId())
                .orElseThrow(NotFound::new);
        return fromOrderDetail(order, customer, delivery, products, bill);
    }

}
