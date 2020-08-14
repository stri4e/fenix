package com.github.orders.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.orders.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {

    @JsonProperty(value = "userId")
    private Long userId;

    @JsonProperty(value = "orderId")
    private Long orderId;

    @JsonProperty(value = "customer")
    private CustomerDto customer;

    @JsonProperty(value = "products")
    private List<ProductDto> products;

    @JsonProperty(value = "amount")
    private BigDecimal amount;

    @JsonProperty(value = "status")
    private OrderStatus status;

    public PurchaseDto(Long userId, OrderDto order) {
        this.userId = userId;
        this.orderId = order.getId();
        this.customer = order.getCustomer();
        this.products = order.getProducts();
        this.amount = order.getAmount();
        this.status = order.getStatus();
    }

}
