package com.github.orders.controllers.impl;

import com.github.orders.controllers.IOrdersDetailPaginationController;
import com.github.orders.dto.CustomerDto;
import com.github.orders.dto.OrderDetailDto;
import com.github.orders.dto.ProductDto;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderItem;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.NotFound;
import com.github.orders.service.ICustomerService;
import com.github.orders.service.IOrderDetailService;
import com.github.orders.service.IProductService;
import com.github.orders.utils.Logging;
import com.github.orders.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.github.orders.utils.TransferObj.fromOrderDetail;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/pages")
public class OrdersDetailPaginationController implements IOrdersDetailPaginationController {

    private final IOrderDetailService orderService;

    private final ICustomerService customerService;

    private final IProductService productService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<OrderDetailDto> findUserOrders(UUID userId, Pageable pageable) {
        return toOrderPage(this.orderService.readUserId(userId, pageable), pageable);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<OrderDetailDto> findNewOrders(OrderStatus status, Pageable pageable) {
        return toOrderPage(this.orderService.readByStatus(status, pageable), pageable);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<OrderDetailDto> findStuffOrders(OrderStatus status, Long staffId, Pageable pageable) {
        return toOrderPage(this.orderService.readByStaffIdAndStatus(staffId, status, pageable), pageable);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<OrderDetailDto> unassignedOrders(Pageable pageable) {
        return toOrderPage(this.orderService.readByStaffIdNull(pageable), pageable);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public Page<OrderDetailDto> findOrdersByCustomer(Long customerId, Pageable pageable) {
        return toOrderPage(this.orderService.readByCustomerId(customerId, pageable), pageable);
    }

    private Page<OrderDetailDto> toOrderPage(Page<OrderDetail> orders, Pageable pageable) {
        List<OrderDetail> content = orders.getContent();
        OrderDetail order = content.stream().findFirst().orElseThrow(NotFound::new);
        CustomerDto customer = this.customerService.readById(order.getCustomerId())
                .orElseThrow(NotFound::new);
        return new PageImpl<>(
                orders.getContent().stream()
                        .map(o -> collect(o, customer))
                        .collect(Collectors.toList()),
                pageable, orders.getTotalElements()
        );
    }

    private OrderDetailDto collect(OrderDetail order, CustomerDto customer) {
        List<OrderItem> items = order.getOrderItems();
        List<ProductDto> products = this.productService.readByIds(
                items.stream()
                        .map(OrderItem::getProductId)
                        .collect(Collectors.toList()))
                .orElseThrow(NotFound::new);
        return fromOrderDetail(order, customer,
                items.stream()
                        .flatMap(o -> products.stream()
                                .filter(p -> p.getId().equals(o.getProductId()))
                                .map(p -> TransferObj.fromOrderItem(o, p)))
                        .collect(Collectors.toList())
        );
    }

}
