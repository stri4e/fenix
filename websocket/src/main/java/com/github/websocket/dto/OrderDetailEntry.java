package com.github.websocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.websocket.payload.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailEntry {

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "customer")
    private CustomerDto customer;

    @NotEmpty
    @JsonProperty(value = "productIds")
    private List<ProductDto> productIds;

    @NotNull
    @JsonProperty(value = "amount")
    private BigDecimal amount;
    @NotNull
    @JsonProperty(value = "userId")
    private Long userId;

    @NotNull
    @JsonProperty(value = "status")
    private OrderStatus status;

}
