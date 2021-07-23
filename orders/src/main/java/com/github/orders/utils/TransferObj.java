package com.github.orders.utils;

import com.github.orders.dto.*;
import com.github.orders.entity.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransferObj {

    public static OrderDetail toOrderDetail(OrderDetailDto data, UUID userId) {
        return new OrderDetail(
                data.getCustomer().getId(),
                data.getOrderItems().stream()
                        .map(TransferObj::toOrderItem)
                        .collect(Collectors.toList()),
                data.getAmount(),
                data.getWeight(),
                data.getCompany(),
                data.getCountry(),
                data.getRegion(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getFlatNumber(),
                data.getZipCode(),
                data.getDeliveryData(),
                data.getDeliveryAmount(),
                userId,
                data.getStatus());
    }

    public static
    OrderDetailDto fromOrderDetail(
            OrderDetail data, OrderDetailDto payload) {
        Map<Long, ProductDto> productsGroupById = payload.getOrderItems().stream()
                .map(OrderItemDto::getProduct)
                .collect(Collectors.toMap(ProductDto::getId, Function.identity()));
        return new OrderDetailDto(
                data.getId(),
                payload.getCustomer(),
                data.getOrderItems().stream()
                        .map(item -> fromOrderItem(item, productsGroupById.get(item.getProductId())))
                        .collect(Collectors.toList()),
                data.getAmount(),
                data.getWeight(),
                data.getCompany(),
                data.getCountry(),
                data.getRegion(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getFlatNumber(),
                data.getZipCode(),
                data.getDeliveryData(),
                data.getDeliveryAmount(),
                data.getStatus()
        );
    }

    public static OrderDetailDto fromOrderDetail(
            OrderDetail data,
            CustomerDto customer,
            List<OrderItemDto> orderItems) {
        return new OrderDetailDto(
                data.getId(),
                customer,
                orderItems,
                data.getAmount(),
                data.getWeight(),
                data.getCompany(),
                data.getCountry(),
                data.getRegion(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getFlatNumber(),
                data.getZipCode(),
                data.getDeliveryData(),
                data.getDeliveryAmount(),
                data.getStatus()
        );
    }

    public static OrderItem toOrderItem(OrderItemDto data) {
        return new OrderItem(
                data.getId(),
                data.getProduct().getId(),
                data.getQuantity(),
                data.getPrice(),
                data.getDiscount()
        );
    }

    public static OrderItemDto fromOrderItem(OrderItem data, ProductDto product) {
        return new OrderItemDto(
                data.getId(),
                product,
                data.getQuantity(),
                data.getPrice(),
                data.getDiscount()
        );
    }

}
