package com.github.bitcoin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrialTransactionDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 8498633217572904625L;

    private String hash;

    private BigInteger value = BigInteger.ZERO;

    private BigInteger fee = BigInteger.ZERO;

    private BigInteger change = BigInteger.ZERO;

    private String from;

    private String to;

}
