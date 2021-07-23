package com.github.orders.controllers.impl;

import com.github.orders.controllers.IOrdersDetailController;
import com.github.orders.dto.CustomerDto;
import com.github.orders.dto.OrderDetailDto;
import com.github.orders.dto.ProductDto;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderItem;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.NotFound;
import com.github.orders.service.*;
import com.github.orders.utils.Logging;
import com.github.orders.utils.Payloads;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.github.orders.entity.OrderStatus.returned;
import static com.github.orders.payload.EmailNotification.registrationOrderNotify;
import static com.github.orders.utils.TransferObj.*;
import static java.util.concurrent.CompletableFuture.runAsync;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class OrdersDetailController implements IOrdersDetailController {

    private final IOrdersNotify ordersNotify;

    private final IEmailService emailService;

    private final IProductService productService;

    private final ICustomerService customerService;

    private final IOrderDetailService orderService;

    private final IOrderItemsService orderItemsService;

    private final ICustomerStatisticsService customerStatisticsService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public OrderDetailDto save(UUID userId, OrderDetailDto payload) {
        OrderDetail order = this.orderService.crete(toOrderDetail(payload, userId));
        return Payloads.of(fromOrderDetail(order, payload))
                .doOnNext(this::andAsync);
    }

    private void andAsync(OrderDetailDto order) {
        Long customerId = order.getCustomer().getId();
        this.ordersNotify.orderNotify(order);
        this.customerStatisticsService.updateTotalOrders(
                customerId, this.orderService.countTotalByCustomerId(customerId)
        );
        this.emailService.registrationOrderNotify(
                registrationOrderNotify(order)
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
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
                        .collect(Collectors.toList())
        ).orElseThrow(NotFound::new);
        Map<Long, ProductDto> productsGroupById = products.stream()
                .collect(Collectors.toMap(ProductDto::getId, Function.identity()));
        return fromOrderDetail(
                order,
                this.customerService.readById(order.getCustomerId())
                        .orElseThrow(NotFound::new),
                orderItems.stream()
                        .map(o -> fromOrderItem(o, productsGroupById.get(o.getProductId())))
                        .collect(Collectors.toList())
        );
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateStatus(Long orderId, OrderStatus status) {
        this.orderService.update(orderId, status);
        if (!status.nonStat()) {
            runAsync(() -> this.statistics(orderId, status));
        }
    }

    private void statistics(Long orderId, OrderStatus status) {
        status.done(
                orderId,
                this.orderService::readCustomerIdByOrderId,
                this.orderService::countSuccessByCustomerId,
                this.customerStatisticsService::updateSuccessOrders
        );
        status.returned(
                orderId,
                this.orderService::readCustomerIdByOrderId,
                this.orderService::countReturnedByCustomerId,
                this.customerStatisticsService::updateReturnedOrders
        );
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
        this.orderService.update(id, returned);
        runAsync(() -> this.statistics(id));
    }

    private void statistics(Long orderId) {
        var customerId = this.orderService.readCustomerIdByOrderId(orderId);
        this.customerStatisticsService.updateReturnedOrders(
                customerId,
                this.orderService.countReturnedByCustomerId(customerId)
        );
    }

}
