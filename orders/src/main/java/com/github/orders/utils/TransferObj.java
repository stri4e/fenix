package com.github.orders.utils;

import com.github.orders.dto.*;
import com.github.orders.entity.Customer;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.BadRequest;
import com.github.orders.exceptions.TypeMessage;
import com.github.orders.payload.Category;
import com.github.orders.payload.Comment;
import com.github.orders.payload.Product;
import com.github.orders.payload.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransferObj {

    public static Customer customer(CustomerDto data) {
        if (Objects.nonNull(data)) {
            return new Customer(
                    data.getCustomerName(),
                    data.getCustomerAddress(),
                    data.getCustomerEmail(),
                    data.getCustomerPhone()
            );
        }
        throw new BadRequest(TypeMessage.badOrderData);
    }

    public static OrderDetail orderDetail(
            Customer c, List<Long> productIds,
            BigDecimal amount, Long userId, OrderStatus status) {
        return new OrderDetail(c, productIds, amount, userId, status);
    }

    public static OrderDetailDto orderDetailDto(OrderDetail data) {
        return new OrderDetailDto(
                data.getId(),
                fromCustomer(data.getCustomer()),
                data.getProductIds(),
                data.getAmount(),
                data.getStatus()
        );
    }

    public static Function<CustomerDto, Customer> orderThrow() {
        return (data) -> new Customer(
                data.getCustomerName(),
                data.getCustomerAddress(),
                data.getCustomerEmail(),
                data.getCustomerPhone()
        );
    }

    public static OrderDetailEntryDto
    fromOrderDetailDto(OrderDetail data, List<Product> products) {
        return new OrderDetailEntryDto(
                data.getId(),
                fromCustomer(data.getCustomer()),
                products.stream()
                        .map(TransferObj::toProductDetail)
                        .collect(Collectors.toList()),
                data.getAmount(),
                data.getUserId(),
                data.getStatus()
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

    public static ProductDetailDto toProductDetail(Product p) {
        return new ProductDetailDto(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getQuantity(),
                p.getDescription(),
                p.getPreviewImage(),
                p.getImages(),
                p.getSpecification().stream()
                        .map(TransferObj::fromSpecification)
                        .collect(Collectors.toList()),
                p.getComments().stream()
                        .map(TransferObj::fromComment)
                        .collect(Collectors.toList()),
                fromCategory(p.getCategory())
        );
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

}
