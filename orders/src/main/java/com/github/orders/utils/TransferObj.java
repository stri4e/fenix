package com.github.orders.utils;

import com.github.orders.dto.*;
import com.github.orders.entity.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TransferObj {

    public static OrderDetail toOrderDetail(OrderDetailDto data, UUID userId) {
        return new OrderDetail(
                data.getCustomer().getId(),
                data.getProducts().stream()
                        .map(ProductDto::getId)
                        .collect(Collectors.toList()),
                data.getAmount(),
                data.getDelivery().getId(),
                userId,
                data.getBill().getId(),
                data.getStatus());
    }

    public static OrderDetailDto fromOrderDetail(
            OrderDetail data, CustomerDto customer, DeliveryDto delivery,
            List<ProductDto> products, BillDto bill) {
        return new OrderDetailDto(
                data.getId(),
                customer,
                products,
                data.getAmount(),
                data.getStatus(),
                delivery,
                bill
        );
    }

}
