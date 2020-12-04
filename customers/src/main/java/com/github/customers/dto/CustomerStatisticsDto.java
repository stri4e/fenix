package com.github.customers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStatisticsDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -8376072108954771875L;

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "customerName")
    private Integer totalOrders;

    @NotNull
    @JsonProperty(value = "customerName")
    private Integer successOrders;

    @NotNull
    @JsonProperty(value = "customerName")
    private Integer returnedOrders;

}
