package com.github.server.mocks.payload

class Customer(
        val id: Long,
        val customerName: String,
        val customerAddress: String,
        val customerPhone: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Customer

        if (id != other.id) return false
        if (customerName != other.customerName) return false
        if (customerAddress != other.customerAddress) return false
        if (customerPhone != other.customerPhone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + customerName.hashCode()
        result = 31 * result + customerAddress.hashCode()
        result = 31 * result + customerPhone.hashCode()
        return result
    }

    override fun toString(): String {
        return "Customer(id=$id, customerName='$customerName', customerAddress='$customerAddress', customerPhone='$customerPhone')"
    }
}