package com.github.admins.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable, Cloneable {

    private static final long serialVersionUID = -8001976834441332571L;

    private Long id;

    private Customer customer;

    private List<Long> productIds;

    private BigDecimal amount;

    private Long userId;

    private OrderStatus status;

    public OrderDetail(Long id,
                       List<Long> productIds,
                       Customer customer,
                       BigDecimal amount,
                       OrderStatus status) {
        this.id = id;
        this.productIds = productIds;
        this.customer = customer;
        this.amount = amount;
        this.status = status;
    }

}
