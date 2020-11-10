package com.github.bitcoin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 7447054344952876288L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "blockHeight")
    private Long blockHeight;

    @NotBlank
    @JsonProperty(value = "blockHash")
    private String blockHash;

    @NotBlank
    @JsonProperty(value = "hash")
    private String hash;

    @NotNull
    @JsonProperty(value = "confirmations")
    private Integer confirmations = 0;

    @NotNull
    @JsonProperty(value = "value")
    private BigInteger value = BigInteger.ZERO;

    @NotNull
    @JsonProperty(value = "inputs")
    private List<String> inputs = new ArrayList<>();

    @NotNull
    @JsonProperty(value = "outputs")
    private List<String> outputs = new ArrayList<>();

}
