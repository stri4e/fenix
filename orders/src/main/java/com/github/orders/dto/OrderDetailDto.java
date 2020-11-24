package com.github.orders.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.orders.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

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
    @JsonProperty(value = "weight")
    private BigDecimal weight;

    @NotBlank
    @JsonProperty(value = "company")
    private String company;

    @NotBlank
    @JsonProperty(value = "country")
    private String country;

    @NotBlank
    @JsonProperty(value = "region")
    private String region;

    @NotBlank
    @JsonProperty(value = "city")
    private String city;

    @NotBlank
    @JsonProperty(value = "street")
    private String street;

    @NotBlank
    @JsonProperty(value = "streetNumber")
    private String streetNumber;

    @JsonProperty(value = "flatNumber")
    private String flatNumber;

    @NotBlank
    @JsonProperty(value = "zipCode")
    private String zipCode;

    @NotBlank
    @JsonProperty(value = "deliveryData")
    private String deliveryData;

    @NotNull
    @JsonProperty(value = "deliveryAmount")
    private BigDecimal deliveryAmount;

    @NotNull
    @JsonProperty(value = "status")
    private OrderStatus status;

    public OrderDetailDto(
            @NotNull CustomerDto customer,
            @NotEmpty List<OrderItemDto> orderItems,
            @NotNull BigDecimal amount,
            @NotNull BigDecimal weight,
            @NotBlank String company,
            @NotBlank String country,
            @NotBlank String region,
            @NotBlank String city,
            @NotBlank String street,
            @NotBlank String streetNumber,
            String flatNumber,
            @NotBlank String zipCode,
            @NotBlank String deliveryData,
            @NotNull BigDecimal deliveryAmount,
            @NotNull OrderStatus status
    ) {
        this.customer = customer;
        this.orderItems = orderItems;
        this.amount = amount;
        this.weight = weight;
        this.company = company;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.flatNumber = flatNumber;
        this.zipCode = zipCode;
        this.deliveryData = deliveryData;
        this.deliveryAmount = deliveryAmount;
        this.status = status;
    }

    public OrderDetailDto and(Consumer<OrderDetailDto> consumer) {
        consumer.accept(this);
        return this;
    }

}
