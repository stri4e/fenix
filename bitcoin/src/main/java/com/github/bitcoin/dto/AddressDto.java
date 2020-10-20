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
public class AddressDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -4872123169803935739L;

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "index")
    private Integer index;

    @NotBlank
    @JsonProperty(value = "address")
    private String address;

    @NotNull
    @JsonProperty(value = "amount")
    private BigInteger amount;

}
