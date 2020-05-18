package com.github.admins.utils;

import com.github.admins.dto.*;
import com.github.admins.payload.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransferObj {

    private TransferObj() {
    }

    public static Category toCategory(CategoryDto data) {
        return new Category(data.getId(), data.getName());
    }

    public static CategoryDto fromCategory(Category data) {
        return new CategoryDto(data.getId(), data.getName());
    }

    public static Comment toComment(CommentDto data) {
        return new Comment(data.getId(), data.getName(), data.getDescription());
    }

    public static CommentDto fromComment(Comment data) {
        return new CommentDto(data.getId(), data.getName(), data.getComment());
    }

    public static Product toProduct(ProductDto data) {
        Product p = new Product();
        BeanUtils.copyProperties(data, p);
        p.setCreateDate(new Date());
        return p;
    }

    public static ProductDto fromProduct(Product data) {
        if (Objects.isNull(data)) {
            return null;
        }
        ProductDto p = new ProductDto();
        BeanUtils.copyProperties(data, p);
        return p;
    }

    public static Specification toSpecification(SpecificationDto data) {
        return new Specification(
            data.getId(),
            data.getName(),
            data.getDescription()
        );
    }

    public static SpecificationDto fromSpecification(Specification data) {
        return new SpecificationDto(
                data.getId(),
                data.getName(),
                data.getDescription()
        );
    }

    public static OrderDetailDto
    fromOrderDetailDto(OrderDetail data, List<Product> products) {
        return new OrderDetailDto(
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

    public static OrderDetail
    toOrderDetail(OrderDetailDto data, List<Long> productIds) {
        return new OrderDetail(
                data.getId(),
                productIds,
                toCustomer(data.getCustomer()),
                data.getAmount(),
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

    public static Customer toCustomer(CustomerDto customer) {
        return new Customer(
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

}
