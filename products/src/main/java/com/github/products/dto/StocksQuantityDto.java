package com.github.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StocksQuantityDto implements Serializable {

    private static final long serialVersionUID = 2472962899429007468L;

    private Long id;

    private StockDto stock;

    private Integer quantity;

}