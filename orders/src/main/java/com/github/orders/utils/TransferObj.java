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
                data.getName(),
                data.getBranches().stream()
                        .map(TransferObj::toBranch)
                        .collect(Collectors.toSet()),
                data.getCities(),
                toPrice(data.getPrice())
        );
    }

    public static CompanyDto fromCompany(Company data) {
        return new CompanyDto(
                data.getId(),
                data.getName(),
                data.getBranches().stream()
                        .map(TransferObj::fromBranch)
                        .collect(Collectors.toSet()),
                data.getCities(),
                fromPrice(data.getPrice())
        );
    }

    public static Branch toBranch(BranchDto data) {
        return new Branch(
                data.getId(),
                data.getNumber(),
                data.getAddress(),
                data.getPhone()
        );
    }

    public static BranchDto fromBranch(Branch data) {
        return new BranchDto(
                data.getId(),
                data.getNumber(),
                data.getAddress(),
                data.getPhone()
        );
    }

    public static Delivery toDelivery(DeliveryDto data) {
        return new Delivery(
                data.getId(),
                data.getType(),
                data.getCompanyName(),
                data.getAddress()
        );
    }

    public static DeliveryDto fromDelivery(Delivery data) {
        return new DeliveryDto(
                data.getId(),
                data.getType(),
                data.getCompanyName(),
                data.getAddress()
        );
    }

    public static Price toPrice(PriceDto data) {
        return new Price(
                data.getId(),
                data.getToHome(),
                data.getToBranch()
        );
    }

    public static PriceDto fromPrice(Price data) {
        return new PriceDto(
                data.getId(),
                data.getToHome(),
                data.getToBranch()
        );
    }

}
