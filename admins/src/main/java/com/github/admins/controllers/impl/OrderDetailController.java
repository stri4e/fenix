package com.github.admins.controllers.impl;

import com.github.admins.controllers.IOrderDetailController;
import com.github.admins.dto.OrderDetailDto;
import com.github.admins.dto.ProductDto;
import com.github.admins.exceptions.NotFound;
import com.github.admins.payload.OrderDetail;
import com.github.admins.payload.OrderStatus;
import com.github.admins.payload.Product;
import com.github.admins.services.IOrderService;
import com.github.admins.services.IProductService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.admins.utils.TransferObj.fromOrderDetailDto;
import static com.github.admins.utils.TransferObj.toOrderDetail;

@RestController
@RequestMapping(path = "/v1/orders")
@RequiredArgsConstructor
public class OrderDetailController implements IOrderDetailController {

    private final IOrderService orderService;

    private final IProductService productService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<OrderDetailDto> findByStatus(OrderStatus status) {
        return this.orderService.readAllByStatus(status)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public OrderDetailDto findById(Long orderId) {
        var order = this.orderService.readById(orderId)
                .orElseThrow(NotFound::new);
        var products = this.productService.readByIds(order.getProductIds())
                .orElseThrow(NotFound::new);
        return fromOrderDetailDto(order, products);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateOrder(@Valid OrderDetailDto payload) {
        var productIds = payload.getProducts().stream()
                .map(ProductDto::getId)
                .collect(Collectors.toList());
        this.orderService.update(toOrderDetail(payload, productIds));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        this.orderService.update(orderId, orderStatus);
    }

    private OrderDetailDto collect(OrderDetail order) {
        List<Product> pr = this.productService.readByIds(order.getProductIds())
                .orElseThrow(NotFound::new);
        return fromOrderDetailDto(
                order,
                pr
        );
    }

}
