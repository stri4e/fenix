package com.github.websocket.restart;

import com.github.websocket.dto.OrderDto;
import com.github.websocket.payload.OrderDetail;
import com.github.websocket.payload.OrderStatus;
import com.github.websocket.payload.Product;
import com.github.websocket.services.IOrderService;
import com.github.websocket.services.IProductService;
import com.github.websocket.utils.CacheNames;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.github.websocket.utils.TransferObj.fromOrderDetail;

@Component
@RequiredArgsConstructor
public class StartUp implements ApplicationRunner {

    private static final int CORE_SIZE = 1;

    private static final int START_DELAY = 0;

    private static final int PERIOD = 60;

    private final CacheManager cacheManager;

    private final IProductService productService;

    private final IOrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Executors.newScheduledThreadPool(CORE_SIZE)
                .scheduleAtFixedRate(this::doCache, START_DELAY, PERIOD, TimeUnit.SECONDS);
    }

    private void doCache() {
        try {
            Cache cache = this.cacheManager.getCache(CacheNames.orders.name());
            List<OrderDetail> orders = this.orderService.readAllByStatus(OrderStatus.open);
            List<OrderDto> result = orders.stream()
                    .map(this::collect).collect(Collectors.toList());
            if (Objects.nonNull(cache)) {
                cache.evictIfPresent(OrderStatus.open);
                cache.putIfAbsent(OrderStatus.open, result);
            }
        } catch (Throwable ignore) {}
    }

    private OrderDto collect(OrderDetail order) {
        List<Product> products = this.productService
                .readByIds(order.getProductIds());
        return fromOrderDetail(order, products);
    }

}
