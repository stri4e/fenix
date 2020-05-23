package com.github.server.mocks.payload

import java.math.BigDecimal
import java.util.*

open class ProductItem(
        var id: Long,
        var name: String,
        var price: BigDecimal,
        var quantity: Int,
        var description: String,
        var previewImage: String,
        var images: List<String>,
        var createDate: Date
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductItem

        if (id != other.id) return false
        if (name != other.name) return false
        if (price != other.price) return false
        if (quantity != other.quantity) return false
        if (description != other.description) return false
        if (previewImage != other.previewImage) return false
        if (images != other.images) return false
        if (createDate != other.createDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + quantity
        result = 31 * result + description.hashCode()
        result = 31 * result + previewImage.hashCode()
        result = 31 * result + images.hashCode()
        result = 31 * result + createDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "Product(id=$id, name='$name'," +
                " price=$price," +
                " quantity=$quantity," +
                " description='$description'," +
                " previewImage='$previewImage'," +
                " images=$images," +
                " createDate=$createDate" +
                ")"
    }


}