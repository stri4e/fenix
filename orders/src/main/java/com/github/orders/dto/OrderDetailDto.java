package com.github.orders.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.orders.entity.OrderStatus;
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

    private static final long serialVersionUID = 7600916691674786492L;

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "customer")
    private CustomerDto customer;

    @NotEmpty
    @JsonProperty(value = "orderItems")
    private List<OrderItemDto> orderItems;

    @NotNull
    @JsonProperty(value = "amount")
    private BigDecimal amount;

    @NotNull
    @JsonProperty(value = "status")
    private OrderStatus status;

    @NotNull
    @JsonProperty(value = "delivery")
    private DeliveryDto delivery;

    public OrderDetailDto(@NotNull CustomerDto customer,
                          @NotEmpty List<OrderItemDto> orderItems,
                          @NotNull BigDecimal amount,
                          @NotNull OrderStatus status) {
        this.customer = customer;
        this.orderItems = orderItems;
        this.amount = amount;
        this.status = status;
    }

    public OrderDetailDto(@NotNull CustomerDto customer,
                          @NotEmpty List<OrderItemDto> orderItems,
                          @NotNull BigDecimal amount,
                          @NotNull OrderStatus status,
                          @NotNull DeliveryDto delivery) {
        this.customer = customer;
        this.orderItems = orderItems;
        this.amount = amount;
        this.status = status;
        this.delivery = delivery;
    }

}
