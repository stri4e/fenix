package com.github.orders.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.orders.entity.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -6600882302610734097L;

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "type")
    private DeliveryType type;

    @NotBlank
    @JsonProperty(value = "companyName")
    private String companyName;

    @NotBlank
    @JsonProperty(value = "address")
    private String address;

    @NotBlank
    @JsonProperty(value = "amount")
    private BigDecimal amount;

}
