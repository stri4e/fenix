package com.github.products.utils;

import com.github.products.dto.*;
import com.github.products.entity.*;

import java.util.stream.Collectors;

public class TransferObj {

    public static Product toProduct(ProductDto data) {
        Product result = new Product();
        result.setId(data.getId());
        result.setName(data.getName());
        result.setPrice(data.getPrice());
        result.setQuantity(data.getQuantity());
        result.setDescription(data.getDescription());
        result.setPreviewImage(data.getPreviewImage());
        result.setImages(data.getImages());
        return result;
    }

    public static ProductDto fromProduct(Product data) {
        return new ProductDto(
                data.getId(),
                data.getBrand().getName(),
                data.getName(),
                data.getPrice(),
                data.getQuantity(),
                data.getDescription(),
                data.getPreviewImage(),
                data.getImages(),
                data.getSpecifications().stream()
                        .map(TransferObj::fromSpecification)
                        .collect(Collectors.toList()),
                data.getComments().stream()
                        .map(TransferObj::fromComment)
                        .collect(Collectors.toList()),
                data.getSubcategory().getName()
        );
    }

    public static SpecificationDto fromSpecification(Specification s) {
        return new SpecificationDto(s.getId(), s.getName(), s.getDescription());
    }

    public static Specification toSpecification(SpecificationDto s) {
        return new Specification(s.getId(), s.getName(), s.getDescription());
    }

    public static CommentDto fromComment(Comment c) {
        return new CommentDto(c.getId(), c.getName(), c.getComment());
    }

    public static Comment toComment(CommentDto c) {
        return new Comment(
                c.getId(),
                c.getName(),
                c.getDescription()
        );
    }

    public static CategoryDto fromCategory(Category data) {
        return new CategoryDto(
                data.getId(),
                data.getName(),
                data.getSubcategories()
        );
    }

    public static Category toCategory(CategoryDto data) {
        return new Category(
                data.getId(),
                data.getName()
        );
    }

    public static Subcategory toSubCategory(SubcategoryDto data) {
        return new Subcategory(
                data.getId(),
                data.getName()
        );
    }

    public static SubcategoryDto fromSubCategory(Subcategory data) {
        return new SubcategoryDto(
                data.getId(),
                data.getName(),
                data.getFilters().stream()
                        .map(TransferObj::fromFilter)
                        .collect(Collectors.toList())
        );
    }

    public static Filter toFilter(FilterDto data) {
        return new Filter(data.getId(), data.getTitle(), data.getCriteria());
    }

    public static FilterDto fromFilter(Filter data) {
        return new FilterDto(
                data.getId(),
                data.getTitle(),
                data.getCriteria()
        );
    }

    public static Brand toBrand(BrandDto data) {
        return new Brand(
                data.getId(),
                data.getName()
        );
    }

    public static BrandDto fromBrand(Brand data) {
        return new BrandDto(
                data.getId(),
                data.getName()
        );
    }

}
