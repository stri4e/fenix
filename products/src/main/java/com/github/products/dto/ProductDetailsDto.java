package com.github.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDto implements Serializable {

    private static final long serialVersionUID = -4031169770161001099L;

    private Long id;
    private String brandName;
    private String name;
    private BigDecimal price;
    private String description;
    private String previewImage;
    private List<String> images;
    private List<CriteriaDto> criteria;
    private ProportionsDto proportions;
    private List<SpecSectionDto> specifications;
    private List<CommentDto> comments;
    private Long subcategoryId;
    private Integer boughtCount;
    private List<StockQuantityDto> links;

}
