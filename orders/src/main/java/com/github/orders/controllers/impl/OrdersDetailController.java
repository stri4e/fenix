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

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.github.orders.payload.EmailNotification.registrationOrderNotify;
import static com.github.orders.utils.TransferObj.*;
import static java.time.LocalDateTime.*;

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
        Customer customer = this.customerService.createOrUpdate(
                toCustomer(payload.getCustomer(), userId));
        Delivery delivery = this.deliveryService.createOrUpdate(
                toDelivery(payload.getDelivery(), userId));
        BillDto bill = this.billService.create(payload.getBill());
        OrderDetail order = this.orderService.crete(
                toOrderDetail(customer, payload, delivery, userId, bill.getId()));
        OrderDetailDto result = fromOrderDetail(order, payload.getProducts(), bill);
        CompletableFuture.runAsync(() -> this.ordersNotify.orderNotify(result));
        CompletableFuture.runAsync(() -> this.emailService.registrationOrderNotify(
                registrationOrderNotify(result)
        ));
        return result;
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<OrderDetailDto> findUserOrders(UUID userId) {
        List<OrderDetail> orders = this.orderService.readUserId(userId);
        return orders.stream()
                .map(this::collect)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<OrderDetailDto> findBindingOrders(Long orderId) {
        OrderDetail order = this.orderService.readById(orderId);
        List<OrderDetail> orders = this.orderService.readUserId(order.getUserId());
        return orders.stream()
                .map(this::collect)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<OrderDetailDto>
    findAllByStatus(OrderStatus status, String start, String end) {
        List<OrderDetail> orders;
        if (Objects.nonNull(start) && Objects.nonNull(end)) {
            orders = this.orderService.read(status, parse(start), parse(end));
        } else {
            orders = this.orderService.readByStatus(status);
        }
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
        BillDto bill = this.billService.findById(order.getBillId());
        return fromOrderDetail(order, products, bill);
    }

}
