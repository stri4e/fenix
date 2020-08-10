package com.github.orders.controllers;

import com.github.orders.dto.OrderDetailDto;
import com.github.orders.dto.OrderDetailEntryDto;
import com.github.orders.entity.Customer;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.BadRequest;
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

import java.util.List;
import java.util.Objects;

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
    public void save(Long userId, OrderDetailDto payload) {
        Customer customer = TransferObj.toCustomer(payload.getCustomer());
        Customer c = this.customerService.create(customer);
        OrderDetail data = TransferObj.toOrderDetail(
                c, payload.getProductsIds(),
                payload.getAmount(), userId, payload.getStatus()
        );
        OrderDetail result = this.orderService.crete(data);
        if (Objects.isNull(result)) {
            throw new BadRequest();
        }
        List<Product> products = this.productService.readByIds(result.getProductIds())
                .orElseThrow(NotFound::new);
        OrderDetailEntryDto pushData = TransferObj.fromOrderDetailDto(result, products);
        this.pushOrders.pushOrder(pushData);
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
    public OrderDetail readById(Long id) {
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
