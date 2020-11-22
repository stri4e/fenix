package com.github.orders.utils;

import com.github.orders.dto.*;
import com.github.orders.entity.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TransferObj {

    public static OrderDetail toOrderDetail(OrderDetailDto data, List<OrderItem> items, UUID userId) {
        return new OrderDetail(
                data.getCustomer().getId(),
                items,
                data.getAmount(),
                data.getDelivery().getId(),
                userId,
                data.getStatus());
    }

    public static OrderDetailDto fromOrderDetail(
            OrderDetail data,
            CustomerDto customer,
            DeliveryDto delivery,
            List<OrderItemDto> orderItems) {
        return new OrderDetailDto(
                data.getId(),
                customer,
                orderItems,
                data.getAmount(),
                data.getStatus(),
                delivery
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

    public static List<OrderItemDto> fromOrderItems(List<OrderItem> newItems, List<OrderItemDto> oldItems) {
        return newItems.stream()
                .flatMap(newItem -> oldItems.stream()
                        .filter(oldItem -> oldItem.getProduct().getId().equals(newItem.getProductId()))
                        .map(up -> fromOrderItem(newItem, up.getProduct())))
                .collect(Collectors.toList());
    }

}
