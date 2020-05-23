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
        specification: Set<Specification>,
        comments: Set<Comment>,
        category: Category,
        status: ProductStatus

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

    constructor(
            id: Long,
            name: String,
            price: BigDecimal,
            quantity: Int,
            description: String,
            previewImage: String,
            images: List<String>,
            createDate: Date
    ) {
        this.id = id
        this.name = name
        this.price = price
        this.quantity = quantity
        this.description = description
        this.previewImage = previewImage
        this.images = images
        this.createDate = createDate
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        return true
    }

}