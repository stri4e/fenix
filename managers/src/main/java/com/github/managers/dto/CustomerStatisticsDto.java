package com.github.managers.dto;

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

    @NotNull(message = "Field is required.")
    @JsonProperty(value = "customerName")
    private Integer totalOrders;

    @NotNull(message = "Field is required.")
    @JsonProperty(value = "successOrders")
    private Integer successOrders;

    @NotNull(message = "Field is required.")
    @JsonProperty(value = "returnedOrders")
    private Integer returnedOrders;

}
