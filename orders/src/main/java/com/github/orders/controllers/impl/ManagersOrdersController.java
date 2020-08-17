package com.github.orders.controllers.impl;

import com.github.orders.controllers.IManagersOrdersController;
import com.github.orders.dto.OrderDto;
import com.github.orders.entity.Manager;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.NotFound;
import com.github.orders.payload.Product;
import com.github.orders.service.IManagerService;
import com.github.orders.service.IOrderDetailService;
import com.github.orders.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.orders.utils.TransferObj.fromOrderDetailDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/handling")
public class ManagersOrdersController implements IManagersOrdersController {

    private final IOrderDetailService orderService;

    private final IManagerService managerService;

    private final IProductService productService;

    @Override
    public void save(Long orderId, OrderStatus orderStatus,
                     Long mangerId, String firstName, String lastName) {
        Manager data = new Manager(mangerId, firstName, lastName);
        Manager manager = this.managerService.create(data);
        OrderDetail order = this.orderService.readById(orderId);
        order.setManager(manager);
        this.orderService.update(order);
    }

    @Override
    public void update(Long orderId, OrderStatus status) {
        this.orderService.update(orderId, status);
    }

    @Override
    public List<OrderDto> findAllOpenOrders(Long managerId, OrderStatus status) {
        List<OrderDetail> orders = this.orderService
                .readByStatusAndManagerId(status, managerId);
        return orders.stream()
                .map(this::collect)
                .collect(Collectors.toList());
    }

    private OrderDto collect(OrderDetail order) {
        List<Product> pr = this.productService.readByIds(order.getProductIds())
                .orElseThrow(NotFound::new);
        return fromOrderDetailDto(
                order,
                pr
        );
    }

}
