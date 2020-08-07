package com.github.server.mocks.services

import com.github.server.mocks.payload.OrderDetail
import com.github.server.mocks.payload.OrderStatus

interface IOrdersService {

    fun readAllByStatus(status: OrderStatus?): List<OrderDetail?>?

    fun readById(id: Long?): OrderDetail?

    fun readByUserId(userId: Long?): List<OrderDetail?>?

    fun update(o: OrderDetail?)

    fun update(productId: Long?, orderStatus: OrderStatus?)

}