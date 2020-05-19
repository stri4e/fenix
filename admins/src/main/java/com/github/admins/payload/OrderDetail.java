package com.github.admins.payload;

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
public class OrderDetail implements Serializable, Cloneable {

    private static final long serialVersionUID = -8001976834441332571L;

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "customer")
    private Customer customer;

    @NotEmpty
    @JsonProperty(value = "productIds")
    private List<Long> productIds;

    @NotNull
    @JsonProperty(value = "amount")
    private BigDecimal amount;

    @NotNull
    @JsonProperty(value = "userId")
    private Long userId;

    @NotNull
    @JsonProperty(value = "status")
    private OrderStatus status;

    public OrderDetail(Long id,
                       List<Long> productIds,
                       Customer customer,
                       BigDecimal amount,
                       OrderStatus status) {
        this.id = id;
        this.productIds = productIds;
        this.customer = customer;
        this.amount = amount;
        this.status = status;
    }

}
