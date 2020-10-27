package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EthereumAccountDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 510255980297499969L;

    @NotBlank
    @JsonProperty(value = "address")
    private String address;

    @NotNull
    @JsonProperty(value = "currencyBalance")
    private BigInteger currencyBalance;

    @NotNull
    @JsonProperty(value = "contractBalance")
    private Map<String, BigInteger> contractBalance;

}
