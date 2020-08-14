package com.github.orders.controllers;

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
    public List<OrderDetail> findAllByStatus(OrderStatus status) {
        return this.orderService.readByStatus(status);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public OrderDetail findByOrderId(Long id) {
        return this.orderService.readById(id);
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

}
