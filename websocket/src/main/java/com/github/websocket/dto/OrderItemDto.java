package com.github.websocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class OrderItemDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 1496604647212070084L;

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "product")
    private ProductDto product;

    @NotNull
    @JsonProperty(value = "quantity")
    private Integer quantity;

    @NotNull
    @JsonProperty(value = "price")
    private BigDecimal price;

    @NotNull
    @JsonProperty(value = "discount")
    private BigDecimal discount;

    public OrderItemDto(Long id, ProductDto product, Integer quantity, BigDecimal price, BigDecimal discount) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.discount = Objects.isNull(discount) ? BigDecimal.ZERO : discount;
    }

}
