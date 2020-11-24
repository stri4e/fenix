package com.github.orders.entity;

import java.util.function.BiConsumer;
import java.util.function.Function;

public enum OrderStatus {
    open, confirm, done, returned;

    public boolean nonStat() {
        return this == OrderStatus.confirm;
    }

    public void done(
            Long orderId,
            Function<Long, Long> funcCustomerId,
            Function<Long, Integer> funcCount,
            BiConsumer<Long, Integer> consumer) {
        if (this == OrderStatus.done) {
            Long customerId = funcCustomerId.apply(orderId);
            consumer.accept(customerId, funcCount.apply(customerId));
        }
    }

    public void returned(
            Long orderId,
            Function<Long, Long> funcCustomerId,
            Function<Long, Integer> funcCount,
            BiConsumer<Long, Integer> consumer) {
        if (this == OrderStatus.returned) {
            Long customerId = funcCustomerId.apply(orderId);
            consumer.accept(customerId, funcCount.apply(customerId));
        }
    }

}
