package com.github.admins.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {

    @NotBlank
    @JsonProperty(value = "from")
    private String from;

    @NotBlank
    @JsonProperty(value = "to")
    private String to;

    @NotNull
    @JsonProperty(value = "value")
    private BigInteger value;

}
