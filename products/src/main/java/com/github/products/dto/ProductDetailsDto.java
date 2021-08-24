package com.github.products.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductDetailsDto extends ProductDto implements Serializable {

    private static final long serialVersionUID = -4031169770161001099L;

    private List<StocksQuantityDto> links;

    private List<CriteriaDto> criteria;

    public ProductDetailsDto(
            Long id,
            @NotBlank(message = "Brand name is required.") String brandName,
            @NotBlank(message = "Name is required.") String name,
            @NotNull(message = "Price is required.") BigDecimal price,
            @NotBlank(message = "Description is required.") String description,
            @NotBlank(message = "Preview Image is required.") String previewImage,
            List<String> images,
            List<SpecSectionDto> specifications,
            @NotBlank(message = "Subcategory Id is required.") Long subcategoryId,
            @NotNull(message = "BoughtCount Name is required.") Integer boughtCount,
            @NotNull ProportionsDto proportions,
            List<StocksQuantityDto> links,
            List<CriteriaDto> criteria) {
        super(id, brandName, name, price, description,
                previewImage, images, specifications,
                subcategoryId, boughtCount, proportions);
        this.links = links;
        this.criteria = criteria;
    }

}
