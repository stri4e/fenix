package com.github.bitcoin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -7377066527998344106L;

    private Long id;

}
