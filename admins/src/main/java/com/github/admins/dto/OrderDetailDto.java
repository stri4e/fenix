package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
    private String status;

}
