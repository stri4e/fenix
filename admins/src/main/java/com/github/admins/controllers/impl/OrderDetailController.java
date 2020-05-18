package com.github.admins.controllers.impl;

import com.github.admins.controllers.IOrderDetailController;
import com.github.admins.dto.OrderDetailDto;
import com.github.admins.dto.ProductDto;
import com.github.admins.payload.OrderDetail;
import com.github.admins.payload.OrderStatus;
import com.github.admins.services.IOrderService;
import com.github.admins.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final IOrderService os;

    private final IProductService ps;

    @Override
    public ResponseEntity<List<OrderDetailDto>>
    ordersByStatus(OrderStatus status) {
        var orders = this.os.readAllByStatus(status);
        var ordersDto = orders.stream()
                .map(this::collect)
                .collect(Collectors.toList());
        return new ResponseEntity<>(ordersDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDetailDto> orderById(Long orderId) {
        var order = this.os.readById(orderId);
        var products = this.ps.readByIds(order.getProductIds());
        return new ResponseEntity<>(
                fromOrderDetailDto(order, products),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<List<OrderDetailDto>> userHistory(Long userId) {
        var history = this.os.readByUserId(userId);
        return new ResponseEntity<>(
                history.stream()
                        .map(this::collect)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Void> updateOrder(@Valid OrderDetailDto payload) {
        var productIds = payload.getProducts().stream()
                .map(ProductDto::getId)
                .collect(Collectors.toList());
        this.os.update(toOrderDetail(payload, productIds));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void>
    updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        this.os.update(orderId, orderStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private OrderDetailDto collect(OrderDetail order) {
        return fromOrderDetailDto(
                order,
                this.ps.readByIds(order.getProductIds())
        );
    }

}
