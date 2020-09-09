package com.github.orders.utils;

import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

public class OrdersSpec {

    public static Specification<OrderDetail> status(OrderStatus status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

}
