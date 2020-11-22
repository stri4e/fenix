package com.github.managers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderDetailDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -1415109953545446323L;

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
            value = "orderItems"
    )
    private List<OrderItemDto> orderItems;

    @NotNull(
            message = "Order amount is required."
    )
    @JsonProperty(
            value = "amount"
    )
    private BigDecimal amount;

    @NotNull(
            message = "Order status is required."
    )
    @JsonProperty(
            value = "status"
    )
    private String status;

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
