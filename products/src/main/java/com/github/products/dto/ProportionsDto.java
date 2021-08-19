package com.github.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProportionsDto implements Serializable {

    private String title;

    private Integer width;

    private Integer height;

    private Integer weight;

    private Integer depth;

}
