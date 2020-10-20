package com.github.bitcoin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrialTransactionDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 8498633217572904625L;

    @NotBlank
    @JsonProperty(value = "hash")
    private String hash;

    @NotNull
    @JsonProperty(value = "value")
    private BigInteger value = BigInteger.ZERO;

    @NotNull
    @JsonProperty(value = "fee")
    private BigInteger fee = BigInteger.ZERO;

    @NotNull
    @JsonProperty(value = "change")
    private BigInteger change = BigInteger.ZERO;

    @NotBlank
    @JsonProperty(value = "from")
    private String from;

    @NotBlank
    @JsonProperty(value = "to")
    private String to;

}
