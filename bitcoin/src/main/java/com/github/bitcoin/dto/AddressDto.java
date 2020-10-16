package com.github.bitcoin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -4872123169803935739L;

    private Long id;

    private Integer index;

    private String address;

    private BigInteger amount;

}
