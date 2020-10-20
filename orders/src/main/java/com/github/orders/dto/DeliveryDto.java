package com.github.orders.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.orders.entity.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {

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

}
