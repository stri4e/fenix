package com.github.orders.controllers;

import com.github.orders.dto.OrderDetailDto;
import com.github.orders.dto.OrderDetailEntryDto;
import com.github.orders.entity.Customer;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.BadRequest;
import com.github.orders.payload.Product;
import com.github.orders.service.ICustomerService;
import com.github.orders.service.IOrderDetailService;
import com.github.orders.service.IProductService;
import com.github.orders.service.IPushOrders;
import com.github.orders.utils.Logging;
import com.github.orders.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Logging(isTime = true, isReturn = false)
    public ResponseEntity<Void> createOrder(Long userId, OrderDetailDto payload) {
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
        List<Product> products = this.productService.readByIds(result.getProductIds());
        OrderDetailEntryDto pushData = TransferObj.fromOrderDetailDto(result, products);
        this.pushOrders.pushOrder(pushData);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public List<OrderDetail> readAllByStatus(OrderStatus status) {
        return this.orderService.readByStatus(status);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public OrderDetail readById(Long id) {
        return this.orderService.readById(id);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public List<OrderDetail> readByUserId(Long userId) {
        return this.orderService.readAllUserId(userId);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void update(OrderDetail payload) {
        this.orderService.update(payload);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void update(Long productId, OrderStatus status) {
        this.orderService.update(productId, status);
    }

}
