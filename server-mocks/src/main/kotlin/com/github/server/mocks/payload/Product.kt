package com.github.server.mocks.payload

import java.math.BigDecimal
import java.util.*

class Product(
        id: Long,
        name: String,
        price: BigDecimal,
        quantity: Int,
        description: String,
        previewImage: String,
        images: List<String>,
        createDate: Date,
        val specification: Set<Specification>,
        val comments: Set<Comment>,
        val category: Category,
        val status: ProductStatus
) : ProductItem(
        id,
        name,
        price,
        quantity,
        description,
        previewImage,
        images,
        createDate
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        return true
    }
}