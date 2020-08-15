package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.admins.payload.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "createdAt")
    private Date createdAt;

    @JsonProperty(value = "customer")
    private CustomerDto customer;

    @JsonProperty(value = "products")
    private List<ProductDto> products;

    @JsonProperty(value = "amount")
    private BigDecimal amount;

    @JsonProperty(value = "status")
    private OrderStatus status;

}
