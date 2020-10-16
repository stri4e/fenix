package com.github.bitcoin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeePerKbDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 7093780096544626196L;

    private Long id;

    private BigInteger fee;

}
