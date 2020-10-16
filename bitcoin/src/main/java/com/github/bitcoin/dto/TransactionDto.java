package com.github.bitcoin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 7447054344952876288L;

    private Long id;

    private Long blockHeight;

    private String blockHash;

    private String hash;

    private Integer confirmations = 0;

    private BigInteger value = BigInteger.ZERO;

    private List<String> inputs = new ArrayList<>();

    private List<String> outputs = new ArrayList<>();

}
