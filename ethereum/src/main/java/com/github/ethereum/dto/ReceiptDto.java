package com.github.ethereum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDto {

    @NotBlank
    @JsonProperty(value = "from")
    private String from;

    @NotBlank
    @JsonProperty(value = "to")
    private String to;

    @NotNull
    @JsonProperty(value = "value")
    private BigDecimal value;

    @NotBlank
    @JsonProperty(value = "contract")
    private String contract;

}
