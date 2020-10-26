package com.github.orders.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 7078479309077676557L;

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "toHome")
    private BigDecimal toHome;

    @JsonProperty(value = "toBranch")
    private BigDecimal toBranch;

}
