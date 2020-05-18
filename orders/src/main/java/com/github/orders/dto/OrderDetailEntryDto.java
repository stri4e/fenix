package com.github.orders.dto;

import com.github.orders.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailEntryDto {

    private Long id;

    private CustomerDto customer;

    private List<ProductDetailDto> productIds;

    private BigDecimal amount;

    private Long userId;

    private OrderStatus status;

}
