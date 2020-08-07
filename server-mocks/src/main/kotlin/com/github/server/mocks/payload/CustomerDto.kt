package com.github.server.mocks.payload

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class CustomerDto {
    @JsonProperty(value = "id")
    private val id: Long? = null

    @JsonProperty(value = "customerName")
    private val customerName: String? = null

    @JsonProperty(value = "customerAddress")
    private val customerAddress: String? = null

    @JsonProperty(value = "customerEmail")
    private val customerEmail: String? = null

    @JsonProperty(value = "customerPhone")
    private val customerPhone: String? = null
}