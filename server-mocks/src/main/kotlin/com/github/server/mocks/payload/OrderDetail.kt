package com.github.server.mocks.payload

import java.math.BigDecimal

class OrderDetail(
        val id: Long? = null,
        val customer: Customer? = null,
        val productIds: List<Long>? = null,
        val amount: BigDecimal? = null,
        val userId: Long? = null,
        val status: OrderStatus? = null
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
        var result = id?.hashCode() ?: 0
        result = 31 * result + (customer?.hashCode() ?: 0)
        result = 31 * result + (productIds?.hashCode() ?: 0)
        result = 31 * result + (amount?.hashCode() ?: 0)
        result = 31 * result + (userId?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "OrderDetail(id=$id, customer=$customer, productIds=$productIds, amount=$amount, userId=$userId, status=$status)"
    }
}