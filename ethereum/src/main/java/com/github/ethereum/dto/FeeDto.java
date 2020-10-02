package com.github.ethereum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeDto implements Serializable, Cloneable {

    @NotNull
    @JsonProperty(value = "fee")
    private BigInteger fee;

    @NotNull
    @JsonProperty(value = "gasPrice")
    private BigInteger gasPrice;

}
