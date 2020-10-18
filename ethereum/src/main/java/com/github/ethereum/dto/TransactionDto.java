package com.github.ethereum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ethereum.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 780118808346247881L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "hash")
    private String hash;

    @NotBlank
    @JsonProperty(value = "blockHash")
    private String blockHash;

    @NotNull
    @JsonProperty(value = "blockNumber")
    private BigInteger blockNumber;

    @NotBlank
    @JsonProperty(value = "contract")
    private String contract;

    @NotBlank
    @JsonProperty(value = "from")
    private String from;

    @NotBlank
    @JsonProperty(value = "to")
    private String to;

    @NotNull
    @JsonProperty(value = "value")
    private BigInteger value;

    @NotNull
    @JsonProperty(value = "fee")
    private BigInteger fee;

    @NotNull
    @JsonProperty(value = "type")
    private TransactionType type;

}
