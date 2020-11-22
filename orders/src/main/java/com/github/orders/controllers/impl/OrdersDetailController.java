package com.github.orders.controllers.impl;

import com.github.orders.controllers.IOrdersDetailController;
import com.github.orders.dto.*;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderItem;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.NotFound;
import com.github.orders.service.*;
import com.github.orders.utils.Logging;
import com.github.orders.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.github.orders.entity.OrderStatus.canceling;
import static com.github.orders.payload.EmailNotification.registrationOrderNotify;
import static com.github.orders.utils.TransferObj.*;
import static java.util.concurrent.CompletableFuture.runAsync;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class OrdersDetailController implements IOrdersDetailController {

    private final IOrderDetailService orderService;

    private final IOrderItemsService orderItemsService;

    private final ICustomerService customerService;

    private final IProductService productService;

    private final IOrdersNotify ordersNotify;

    private final IDeliveryService deliveryService;

    private final IEmailService emailService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public OrderDetailDto save(UUID userId, OrderDetailDto payload) {
        List<OrderItem> items = this.orderItemsService.createAll(
                payload.getOrderItems().stream()
                        .map(TransferObj::toOrderItem)
                        .collect(Collectors.toList()));
        OrderDetailDto result = fromOrderDetail(
                this.orderService.crete(toOrderDetail(payload, items, userId)),
                payload.getCustomer(),
                payload.getDelivery(),
                fromOrderItems(items, payload.getOrderItems())
        );
        runAsync(() -> this.ordersNotify.orderNotify(result));
        runAsync(() -> this.emailService.registrationOrderNotify(
                registrationOrderNotify(result)
        ));
        return result;
    }

    @Override
    public OrderStatus[] findAllOrderStatus() {
        return OrderStatus.values();
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public OrderDetailDto findById(Long id) {
        OrderDetail order = this.orderService.readById(id);
        List<OrderItem> orderItems = order.getOrderItems();
        List<ProductDto> products = this.productService.readByIds(
                orderItems.stream()
                        .map(OrderItem::getProductId)
                        .collect(Collectors.toList()))
                .orElseThrow(NotFound::new);
        CustomerDto customer = this.customerService.readById(order.getCustomerId())
                .orElseThrow(NotFound::new);
        DeliveryDto delivery = this.deliveryService.readById(order.getDeliveryId())
                .orElseThrow(NotFound::new);
        return fromOrderDetail(order, customer, delivery,
                orderItems.stream()
                        .flatMap(o -> products.stream()
                                .filter(p -> p.getId().equals(o.getProductId()))
                                .map(p -> fromOrderItem(o, p))
                        ).collect(Collectors.toList()));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateStatus(Long orderId, OrderStatus status) {
        this.orderService.update(orderId, status);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void assignManager(Long orderId, Long staffId) {
        this.orderService.updateOrderManager(orderId, staffId);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.orderService.update(id, canceling);
    }

}
