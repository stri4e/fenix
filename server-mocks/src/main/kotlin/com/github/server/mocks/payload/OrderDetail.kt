package com.github.server.mocks.payload

import java.math.BigDecimal

class OrderDetail(
        val id: Long,
        val customer: Customer,
        val productIds: List<Product>,
        val amount: BigDecimal,
        val userId: Long,
        val status: OrderStatus
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderDetail

        if (id != other.id) return false
        if (customer != other.customer) return false
        if (productIds != other.productIds) return false
        if (amount != other.amount) return false
        if (userId != other.userId) return false
        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + customer.hashCode()
        result = 31 * result + productIds.hashCode()
        result = 31 * result + amount.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }

    override fun toString(): String {
        return "OrderDetail(id=$id, customer=$customer, productIds=$productIds, amount=$amount, userId=$userId, status=$status)"
    }

}