package com.github.customers.utils;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface Payloads<T> {

    static <T> Payloads<T> of(T payload) {
        if (Objects.isNull(payload)) {
            throw new NullPointerException();
        }
        return new PayloadsDefault<>(payload);
    }

    static void async(Runnable runnable) {
        CompletableFuture.runAsync(runnable);
    }

    T doOnNext(Consumer<T> consumer);

    T doOnNext(Runnable runnable);

    class PayloadsDefault<T> implements Payloads<T> {

        private final T payload;

        private PayloadsDefault(T payload) {
            this.payload = payload;
        }

        @Override
        public T doOnNext(Consumer<T> consumer) {
            CompletableFuture.runAsync(() -> consumer.accept(this.payload));
            return this.payload;
        }

        @Override
        public T doOnNext(Runnable runnable) {
            CompletableFuture.runAsync(runnable);
            return this.payload;
        }
    }

}
