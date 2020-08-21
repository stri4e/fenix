package com.github.orders.controllers.impl;

import com.github.orders.controllers.IOrdersDetailController;
import com.github.orders.dto.OrderDetailDto;
import com.github.orders.dto.OrderDto;
import com.github.orders.dto.PurchaseDto;
import com.github.orders.entity.Customer;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.NotFound;
import com.github.orders.payload.Product;
import com.github.orders.service.*;
import com.github.orders.utils.Logging;
import com.github.orders.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class OrdersDetailController implements IOrdersDetailController {

    private final ICustomerService customerService;

    private final IOrderDetailService orderService;

    private final IProductService productService;

    private final IPushOrders pushOrders;

    private final IPurchaseService purchaseService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void save(Long userId, OrderDetailDto payload) {
        Customer c = TransferObj.toCustomer(payload.getCustomer());
        Customer customer = this.customerService.create(c);
        OrderDetail data = TransferObj.toOrderDetail(customer, payload, userId);
        OrderDetail result = this.orderService.crete(data);
        List<Product> products = this.productService.readByIds(result.getProductIds())
                .orElseThrow(NotFound::new);
        OrderDto pushData = TransferObj.fromOrderDetailDto(result, products);
        this.pushOrders.pushOrder(pushData);
        PurchaseDto purchase = new PurchaseDto(userId, pushData);
        this.purchaseService.createPurchase(purchase);
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
    public void update(OrderDetail payload) {
        this.orderService.update(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(Long orderId, OrderStatus status) {
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
