package com.github.orders.utils;

import com.github.orders.dto.*;
import com.github.orders.entity.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TransferObj {

    public static Customer toCustomer(CustomerDto data, UUID userId) {
        return new Customer(
                data.getId(),
                data.getCustomerName(),
                data.getCustomerEmail(),
                data.getCustomerPhone(),
                userId
        );
    }

    public static OrderDetail toOrderDetail(
            Customer customer, OrderDetailDto data, Delivery delivery, UUID userId, Long billId) {
        return new OrderDetail(
                customer,
                data.getProducts().stream()
                        .map(ProductDto::getId)
                        .collect(Collectors.toList()),
                data.getAmount(),
                delivery,
                userId,
                billId,
                data.getStatus());
    }

    public static OrderDetailDto fromOrderDetail(OrderDetail data, List<ProductDto> products, BillDto bill) {
        return new OrderDetailDto(
                data.getId(),
                fromCustomer(data.getCustomer()),
                products,
                data.getAmount(),
                data.getStatus(),
                fromDelivery(data.getDelivery()),
                bill
        );
    }

    public static CustomerDto fromCustomer(Customer data) {
        return new CustomerDto(
                data.getId(),
                data.getCustomerName(),
                data.getCustomerEmail(),
                data.getCustomerPhone(),
                fromAddress(data.getAddress())
        );
    }

    public static Delivery toDelivery(DeliveryDto data, UUID userId) {
        return new Delivery(
                data.getId(),
                data.getType(),
                data.getCompanyName(),
                data.getAmount(),
                userId
        );
    }

    public static DeliveryDto fromDelivery(Delivery data) {
        return new DeliveryDto(
                data.getId(),
                data.getType(),
                data.getCompanyName(),
                fromAddress(data.getAddress()),
                data.getAmount()
        );
    }

    public static Address toAddress(AddressDto data, UUID userId) {
        return new Address(
                data.getId(),
                data.getCountry(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getFlatNumber(),
                data.getState(),
                data.getZipCode(),
                data.getType(),
                userId
        );
    }

    public static AddressDto fromAddress(Address data) {
        return new AddressDto(
                data.getId(),
                data.getCountry(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getFlatNumber(),
                data.getState(),
                data.getZipCode(),
                data.getType()
        );
    }

}
