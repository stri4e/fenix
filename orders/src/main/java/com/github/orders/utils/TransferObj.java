package com.github.orders.utils;

import com.github.orders.dto.*;
import com.github.orders.entity.*;
import com.github.orders.payload.Category;
import com.github.orders.payload.Comment;
import com.github.orders.payload.Product;
import com.github.orders.payload.Specification;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
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
            Customer c, OrderDetailDto data, Long userId) {
        return new OrderDetail(
                c, data.getProductsIds(),
                data.getAmount(), userId, data.getStatus());
    }

    public static OrderDto
    fromOrderDetailDto(OrderDetail data, List<Product> products, Delivery delivery) {
        var customer = fromCustomer(data.getCustomer());
        var productsDto = products.stream()
                .map(TransferObj::fromProduct)
                .collect(Collectors.toList());
        return new OrderDto(
                data.getId(),
                customer,
                productsDto,
                data.getAmount(),
                data.getStatus(),
                fromDelivery(delivery)
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

    public static ProductDto fromProduct(Product data) {
        if (Objects.isNull(data)) {
            return null;
        }
        ProductDto p = new ProductDto();
        BeanUtils.copyProperties(data, p);
        return p;
    }

    public static SpecificationDto fromSpecification(Specification data) {
        return new SpecificationDto(
                data.getId(),
                data.getName(),
                data.getDescription()
        );
    }

    public static CommentDto fromComment(Comment data) {
        return new CommentDto(data.getId(), data.getName(), data.getComment());
    }

    public static CategoryDto fromCategory(Category data) {
        return new CategoryDto(data.getId(), data.getName());
    }

    public static Company toCompany(CompanyDto data) {
        return new Company(
                data.getId(),
                data.getName(),
                data.getBranches().stream()
                        .map(TransferObj::toBranch)
                        .collect(Collectors.toSet()),
                data.getCities(),
                data.getHomePrice(),
                data.getBranchPrice()
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
                data.getHomePrice(),
                data.getBranchPrice()
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
                data.getBranchAddress()
        );
    }

    public static DeliveryDto fromDelivery(Delivery data) {
        return new DeliveryDto(
                data.getId(),
                data.getType(),
                data.getCompanyName(),
                data.getBranchAddress()
        );
    }

}
