package com.github.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -467940017733243523L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "time")
    private String time;

    @NotBlank
    @JsonProperty(value = "assetNameQuote")
    private String assetNameQuote;

    @NotNull
    @JsonProperty(value = "rate")
    private Double rate;

}
