package com.github.products.utils;

import com.github.products.dto.*;
import com.github.products.entity.*;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TransferObj {

    private TransferObj() {
    }

    public static Product toProduct(ProductDto data) {
        Product result = new Product();
        result.setId(data.getId());
        result.setVinCode(data.getVinCode());
        result.setName(data.getName());
        result.setPrice(data.getPrice());
        result.setDescription(data.getDescription());
        result.setPreviewImage(data.getPreviewImage());
        result.setImages(data.getImages());
        result.setProportions(toProportions(data.getProportions()));
        return result;
    }

    public static ProductDto fromProduct(Product data) {
        Map<Stock, Integer> quantities = data.getStocksQuantity();
        return new ProductDto(
                data.getId(),
                data.getVinCode(),
                data.getBrand().getName(),
                data.getName(),
                data.getPrice(),
                data.getDescription(),
                data.getPreviewImage(),
                data.getImages(),
                data.getSpecSections().stream()
                        .map(TransferObj::fromSpecSection)
                        .collect(Collectors.toList()),
                data.getSubcategory().getId(),
                data.getBoughtCount(),
                fromProportions(data.getProportions()),
                quantities.keySet().stream()
                        .collect(Collectors.toMap(Stock::getId, quantities::get))
        );
    }

    public static SpecSectionDto fromSpecSection(SpecSection data) {
        return new SpecSectionDto(
                data.getId(),
                data.getTitle(),
                data.getSpecifications().stream()
                        .map(TransferObj::fromSpecification)
                        .collect(Collectors.toList())
        );
    }

    public static Proportions toProportions(ProportionsDto proportions) {
        return new Proportions(
                proportions.getTitle(),
                proportions.getWidth(),
                proportions.getHeight(),
                proportions.getWeight(),
                proportions.getDepth()
        );
    }

    public static ProportionsDto fromProportions(Proportions proportions) {
        return new ProportionsDto(
                proportions.getTitle(),
                proportions.getWidth(),
                proportions.getHeight(),
                proportions.getWeight(),
                proportions.getDepth()
        );
    }

    public static SpecificationDto fromSpecification(Specification s) {
        return new SpecificationDto(s.getId(), s.getName(), s.getDescription());
    }

    public static Specification toSpecification(SpecificationDto s) {
        return new Specification(s.getId(), s.getName(), s.getDescription());
    }

    public static CommentDto fromComment(Comment c) {
        return new CommentDto(c.getId(), c.getFirstName(), c.getLastName(), c.getText());
    }

    public static Comment toComment(CommentDto c, UUID userId) {
        return new Comment(
                c.getId(),
                c.getFirstName(),
                c.getLastName(),
                userId,
                c.getText()
        );
    }

    public static CategoryDto fromCategory(Category data) {
        return new CategoryDto(
                data.getId(),
                data.getPreviewImage(),
                data.getName(),
                data.getSubcategories().stream()
                        .map(TransferObj::fromSubCategory)
                        .collect(Collectors.toList())
        );
    }

    public static Category toCategory(CategoryDto data) {
        return new Category(
                data.getId(),
                data.getPreviewImage(),
                data.getName()
        );
    }

    public static Subcategory toSubcategory(SubcategoryDto data) {
        return new Subcategory(
                data.getId(),
                data.getName()
        ).addFilters(data.getFilters().stream()
                .map(TransferObj::toFilter)
                .collect(Collectors.toList())
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
        return new Filter(data.getId(), data.getTitle()).addCriteria(
                data.getCriteria().stream()
                        .map(TransferObj::toCriteria)
                        .collect(Collectors.toList())
        );
    }

    public static FilterDto fromFilter(Filter data) {
        return new FilterDto(
                data.getId(),
                data.getTitle(),
                data.getCriteria().stream()
                        .map(TransferObj::fromCriteria)
                        .collect(Collectors.toList())
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

    public static Criteria toCriteria(CriteriaDto data) {
        return new Criteria(
                data.getId(),
                data.getValue()
        );
    }

    public static CriteriaDto fromCriteria(Criteria data) {
        return new CriteriaDto(
                data.getId(),
                data.getValue()
        );
    }

    public static Stock toStock(StockDto data) {
        return new Stock(
                data.getId(),
                data.getName(),
                data.getNumber(),
                data.getPhone(),
                data.getEmail(),
                data.getStaffs().stream()
                        .map(staff -> new StockStaff(
                                staff.getId(),
                                staff.getFirstName(),
                                staff.getLastName(),
                                staff.getEmail(),
                                staff.getPhones()
                        )).collect(Collectors.toSet()),
                data.getCountry(),
                data.getRegion(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getZipCode()
        );
    }

    public static StockDto fromStock(Stock data) {
        return new StockDto(
                data.getId(),
                data.getName(),
                data.getNumber(),
                data.getPhone(),
                data.getEmail(),
                data.getStaffs().stream()
                        .map(staff -> new StockStaffDto(
                                staff.getId(),
                                staff.getFirstName(),
                                staff.getLastName(),
                                staff.getEmail(),
                                staff.getPhones()
                        ))
                        .collect(Collectors.toSet()),
                data.getCountry(),
                data.getRegion(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getZipCode()
        );
    }

}
