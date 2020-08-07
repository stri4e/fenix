package com.github.server.mocks.controllers

import com.github.server.mocks.payload.OrderDetail
import com.github.server.mocks.payload.OrderStatus
import com.github.server.mocks.services.IOrdersService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/v1/details"])
class OrdersController(private val orderService: IOrdersService) {

    @GetMapping(
            path = ["/fetch/{status}"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun readAllByStatus(@PathVariable status: OrderStatus?): List<OrderDetail?>? {
        return orderService.readAllByStatus(status)
    }

    @GetMapping(
            path = ["/fetch"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun readById(@RequestParam(name = "orderId") orderId: Long?): OrderDetail? {
        return orderService.readById(orderId)
    }

//    @GetMapping(
//            path = ["/fetch/{userId}"],
//            produces = [MediaType.APPLICATION_JSON_VALUE]
//    )
//    fun readByUserId(@PathVariable userId: Long?): List<OrderDetail?>? {
//        return orderService.readByUserId(userId)
//    }

    @PutMapping(path = ["/edit/"])
    fun update(@RequestBody o: OrderDetail?) {
        orderService.update(o)
    }

    @PutMapping(path = ["/edit/{productId}/{orderStatus}"])
    fun update(@PathVariable productId: Long?, @PathVariable orderStatus: OrderStatus?) {
        orderService.update(productId, orderStatus)
    }

}