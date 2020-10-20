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
    fromOrderDetailDto(OrderDetail data, List<Product> products, BillDto bill) {
        return new OrderDetailDto(
                data.getId(),
                fromCustomer(data.getCustomer()),
                products.stream()
                        .map(TransferObj::fromProduct)
                        .collect(Collectors.toList()),
                data.getAmount(),
                data.getUserId(),
                data.getStatus(),
                fromDelivery(data.getDelivery()),
                bill
        );
    }

    public static OrderDetail
    toOrderDetail(OrderDetailDto data, List<Long> productIds) {
        return new OrderDetail(
                data.getId(),
                toCustomer(data.getCustomer()),
                productIds,
                data.getAmount(),
                data.getUserId(),
                data.getBill().getId(),
                data.getStatus(),
                toDelivery(data.getDelivery())
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
