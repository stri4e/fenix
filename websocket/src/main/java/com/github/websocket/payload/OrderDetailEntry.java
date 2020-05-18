package com.github.websocket.payload;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDetailEntry {

    private Long id;

    private Customer customer;

    private List<ProductDetail> productIds;

    private BigDecimal amount;

    private Long userId;

    private OrderStatus status;

}
