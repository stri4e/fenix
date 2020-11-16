package com.github.websocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.websocket.payload.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 942104612974879832L;

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

    @NotNull
    @JsonProperty(value = "bill")
    private BillDto bill;

}
