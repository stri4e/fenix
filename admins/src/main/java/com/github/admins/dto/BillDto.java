package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 3276968318601414177L;

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "amount")
    private BigInteger amount;

    @NotNull
    @JsonProperty(value = "amountPaid")
    private BigInteger amountPaid;

    @NotBlank
    @JsonProperty(value = "asset")
    private String assetName;

    @NotBlank
    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "transfers")
    private List<String> transfers = new ArrayList<>();

    @NotBlank
    @JsonProperty(value = "paymentType")
    private String paymentType;

    @NotNull
    @JsonProperty(value = "billType")
    private BillType billType;

    @NotNull
    @JsonProperty(value = "who")
    private WhoDto who;

    @NotNull
    @JsonProperty(value = "whom")
    private WhomDto whom;

}
