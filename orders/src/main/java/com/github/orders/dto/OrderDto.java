package com.github.orders.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.orders.entity.OrderStatus;
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
public class OrderDto {

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "customer")
    private CustomerDto customer;

    @NotEmpty
    @JsonProperty(value = "products")
    private List<ProductDto> products;

    @NotNull
    @JsonProperty(value = "amount")
    private BigDecimal amount;

    @NotNull
    @JsonProperty(value = "status")
    private OrderStatus status;

    @NotNull
    @JsonProperty(value = "delivery")
    private DeliveryDto delivery;

}
