package com.github.server.mocks.services.impl

import com.github.server.mocks.payload.Customer
import com.github.server.mocks.payload.OrderDetail
import com.github.server.mocks.payload.OrderStatus
import com.github.server.mocks.services.IOrdersService
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.stream.Collectors

@Service
class OrdersService : IOrdersService {

    var ordersMock: List<OrderDetail> = listOf(
            OrderDetail(
                  1,
                    Customer(
                            1,
                            "Albert Albert",
                            "Bolotnaj street 8",
                            "albert@yandex.ru",
                            "+89752323"
                    ),
                    listOf(1, 2, 3, 4, 5),
                    BigDecimal("12.2"),
                    1,
                    OrderStatus.open
            ),
            OrderDetail(
                    2,
                    Customer(
                            2,
                            "Zigmud Tupizin",
                            "River street 8",
                            "zigmud@gmail.com",
                            "+8934223"
                    ),
                    listOf(1, 2, 3, 4, 5),
                    BigDecimal("220.254"),
                    2,
                    OrderStatus.open
            ),
            OrderDetail(
                    3,
                    Customer(
                            3,
                            "Klava Marinez",
                            "Suspect street 8",
                            "klava@rambler.ru",
                            "+897527896"
                    ),
                    listOf(1, 2, 3, 4, 5),
                    BigDecimal("569.205"),
                    3,
                    OrderStatus.handling
            ),
            OrderDetail(
                    4,
                    Customer(
                            4,
                            "Jolobock Ivanich",
                            "Tupizina street 8",
                            "jolobock@gmail.com",
                            "+87892323"
                    ),
                    listOf(1, 2, 3, 4, 5),
                    BigDecimal("1111.11"),
                    4,
                    OrderStatus.open
            ),
            OrderDetail(
                    5,
                    Customer(
                            5,
                            "Kozulinskiy Peronion",
                            "Bolotnaj street 18",
                            "kozulinskiy@yandex.com",
                            "+897543223"
                    ),
                    listOf(1, 2, 3, 4, 5),
                    BigDecimal("123432.2432"),
                    5,
                    OrderStatus.close
            )
    )

    override fun readAllByStatus(status: OrderStatus?): List<OrderDetail?>? {
        return ordersMock.stream().filter { v -> v.status == status }.collect(Collectors.toList())
    }

    override fun readById(id: Long?): OrderDetail? {
        return ordersMock.stream().filter { o -> id == o.id }.findFirst().orElseThrow()
    }

    override fun readByUserId(userId: Long?): List<OrderDetail?>? {
        return ordersMock.stream().filter { o -> o.userId == userId }.collect(Collectors.toList())
    }

    override fun update(o: OrderDetail?) {
    }

    override fun update(productId: Long?, orderStatus: OrderStatus?) {
    }

}