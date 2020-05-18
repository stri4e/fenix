package com.github.orders.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderDetailDto {

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "customer")
    private CustomerDto customer;

    @NotEmpty
    @JsonProperty(value = "productsIds")
    private List<Long> productsIds;

    @NotNull
    @JsonProperty(value = "amount")
    private BigDecimal amount;

    @NotNull
    @JsonProperty(value = "status")
    private OrderStatus status;

    public OrderDetailDto(@NotNull CustomerDto customer,
                          @NotEmpty List<Long> productsIds,
                          @NotNull BigDecimal amount,
                          @NotNull OrderStatus status) {
        this.customer = customer;
        this.productsIds = productsIds;
        this.amount = amount;
        this.status = status;
    }

}
