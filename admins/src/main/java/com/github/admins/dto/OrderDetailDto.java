package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.admins.payload.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderDetailDto {

    @JsonProperty(
            value = "id"
    )
    private Long id;

    @NotNull(
            message = "Customer data is required."
    )
    @JsonProperty(
            value = "customer"
    )
    private CustomerDto customer;

    @NotEmpty(
            message = "Products ids is required."
    )
    @JsonProperty(
            value = "productsIds"
    )
    private List<ProductDto> products;

    @NotNull(
            message = "Order amount is required."
    )
    @JsonProperty(
            value = "amount"
    )
    private BigDecimal amount;

    @JsonProperty(
            value = "userId"
    )
    private Long userId;

    @NotNull(
            message = "Order status is required."
    )
    @JsonProperty(
            value = "status"
    )
    private OrderStatus status;

    @NotNull(
            message = "Delivery is required."
    )
    @JsonProperty(value = "delivery")
    private DeliveryDto delivery;

    @NotNull(
            message = "Bill is required."
    )
    @JsonProperty(value = "bill")
    private BillDto bill;

}
