package com.github.orders.utils;

import com.github.orders.dto.*;
import com.github.orders.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class TransferObj {

    public static Customer toCustomer(CustomerDto data) {
        return new Customer(
                data.getCustomerName(),
                data.getCustomerAddress(),
                data.getCustomerEmail(),
                data.getCustomerPhone()
        );
    }

    public static OrderDetail toOrderDetail(
            Customer customer, OrderDetailDto data, Delivery delivery, Long userId, Long billId) {

        List<Long> r = data.getProducts().stream()
                .map(ProductDto::getId)
                .collect(Collectors.toList());

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

    public static CustomerDto fromCustomer(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getCustomerName(),
                customer.getCustomerAddress(),
                customer.getCustomerEmail(),
                customer.getCustomerPhone()
        );
    }

    public static Company toCompany(CompanyDto data) {
        return new Company(
                data.getId(),
                data.getName()
        );
    }

    public static CompanyDto fromCompany(Company data) {
        return new CompanyDto(
                data.getId(),
                data.getName()
        );
    }

    public static Delivery toDelivery(DeliveryDto data) {
        return new Delivery(
                data.getId(),
                data.getType(),
                data.getCompanyName(),
                data.getAddress(),
                data.getAmount()
        );
    }

    public static DeliveryDto fromDelivery(Delivery data) {
        return new DeliveryDto(
                data.getId(),
                data.getType(),
                data.getCompanyName(),
                data.getAddress(),
                data.getAmount()
        );
    }

}
