package com.github.products.utils;

import com.github.products.dto.CategoryDto;
import com.github.products.dto.CommentDto;
import com.github.products.dto.ProductDto;
import com.github.products.dto.SpecificationDto;
import com.github.products.entity.Category;
import com.github.products.entity.Comment;
import com.github.products.entity.Product;
import com.github.products.entity.Specification;

import java.util.Objects;
import java.util.stream.Collectors;

public class TransferObj {

    public static ProductDto fromProduct(Product p) {
        if (Objects.isNull(p)) {
            return null;
        }
        ProductDto product = new ProductDto();
        product.setId(p.getId());
        product.setName(p.getName());
        product.setPrice(p.getPrice());
        product.setQuantity(p.getQuantity());
        product.setDescription(p.getDescription());
        product.setPreviewImage(p.getPreviewImage());
        product.setImages(p.getImages());
        product.setSpecifications(
                p.getSpecification()
                        .stream().map(TransferObj::fromSpec)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );
        product.setComments(
                p.getComments()
                        .stream().map(TransferObj::fromComment)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );
        product.setCategoryName(p.getCategory().getName());
        return product;
    }

    public static SpecificationDto fromSpec(Specification s) {
        if (Objects.isNull(s)) {
            return null;
        }
        return new SpecificationDto(s.getId(), s.getName(), s.getDescription());
    }

    public static CommentDto fromComment(Comment c) {
        if (Objects.isNull(c)) {
            return null;
        }
        return new CommentDto(c.getId(), c.getName(), c.getComment());
    }

    public static CategoryDto fromCategory(Category c) {
        if (Objects.isNull(c)) {
            return null;
        }
        return new CategoryDto(c.getId(), c.getName());
    }

    public static Comment toComment(CommentDto c) {
        if (Objects.isNull(c)) {
            return null;
        }
        return new Comment(
                c.getId(),
                c.getName(),
                c.getDescription()
        );
    }

}
