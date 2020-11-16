package com.github.bitcoin.dto;

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
public class FeePerKbDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 7093780096544626196L;

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "fee")
    private BigInteger fee;

}
