package com.github.bitcoin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.bitcoin.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 4729447471412394620L;

    private Long id;

    @NotNull
    @JsonProperty(value = "amount")
    private BigInteger amount;

    @NotNull
    @JsonProperty(value = "addresses")
    private List<AddressDto> addresses;

    @NotNull
    @JsonProperty(value = "transactions")
    private List<TransactionDto> transactions;

}
