package com.github.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecSectionDto implements Serializable {

    private static final long serialVersionUID = -7567013660310937952L;

    private Long id;

    private String title;

    private List<SpecificationDto> specification;

}
