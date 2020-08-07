package com.github.server.mocks.payload

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.math.BigDecimal

@Data
@AllArgsConstructor
@NoArgsConstructor
class ProductDto {
    @JsonProperty(value = "id")
    private val id: Long? = null

    @JsonProperty(value = "name")
    private val name: String? = null

    @JsonProperty(value = "price")
    private val price: BigDecimal? = null

    @JsonProperty(value = "quantity")
    private val quantity: Int? = null

    @JsonProperty(value = "description")
    private val description: String? = null

    @JsonProperty(value = "previewImage")
    private val previewImage: String? = null

    @JsonProperty(value = "images")
    private val images: List<String>? = null
}