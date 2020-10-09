package com.github.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentRateDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -7937398303744269975L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "assetName")
    private String assetName;

    @NotNull
    @JsonProperty(value = "rates")
    private List<RateDto> rates;

}
