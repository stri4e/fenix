package com.github.server.mocks.payload

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.math.BigDecimal

@Data
@AllArgsConstructor
@NoArgsConstructor
class OrderDetailEntryDto {
    @JsonProperty(value = "id")
    private val id: Long? = null

    @JsonProperty(value = "customer")
    private val customer: CustomerDto? = null

    @JsonProperty(value = "productIds")
    private val productIds: List<ProductDto>? = null

    @JsonProperty(value = "amount")
    private val amount: BigDecimal? = null

    @JsonProperty(value = "userId")
    private val userId: Long? = null

    @JsonProperty(value = "status")
    private val status: OrderStatus? = null
}