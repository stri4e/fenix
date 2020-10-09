package com.github.payments.payload;

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
public class Report implements Serializable, Cloneable {

    private static final long serialVersionUID = -1815219686485439228L;

    @NotNull
    @JsonProperty(value = "amount")
    private BigInteger amount;

    @NotNull
    @JsonProperty(value = "amountPaid")
    private BigInteger amountPaid;

    @NotNull
    @JsonProperty(value = "different")
    private BigInteger different;

    @NotNull
    @JsonProperty(value = "status")
    private ReportStatus status;

}
