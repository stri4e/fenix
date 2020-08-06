package com.github.server.mocks.payload

import java.math.BigDecimal
import java.util.*

class Product : ProductItem {

    var specification: Set<Specification>? = null
    var comments: Set<Comment>? = null
    var category: Category? = null
    var status: ProductStatus? = null

    constructor(
            id: Long,
            name: String,
            price: BigDecimal,
            quantity: Int,
            description: String,
            previewImage: String,
            images: List<String>,
            createDate: Date,
            specification: Set<Specification>?,
            comments: Set<Comment>?,
            category: Category?,
            status: ProductStatus?
    ) : super(id, name, price, quantity, description, previewImage, images, createDate) {
        this.specification = specification
        this.comments = comments
        this.category = category
        this.status = status
    }

    constructor(
            id: Long,
            name: String,
            price: BigDecimal,
            quantity: Int,
            description: String,
            previewImage: String,
            images: List<String>,
            createDate: Date,
            nothing: Nothing?
    ) : super(id, name, price, quantity, description, previewImage, images, createDate)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        return true
    }

}