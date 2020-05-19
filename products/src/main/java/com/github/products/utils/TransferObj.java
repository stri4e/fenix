package com.github.products.utils;

import com.github.products.dto.CategoryDto;
import com.github.products.dto.CommentDto;
import com.github.products.dto.ProductDto;
import com.github.products.dto.SpecificationDto;
import com.github.products.entity.Category;
import com.github.products.entity.Comment;
import com.github.products.entity.Product;
import com.github.products.entity.Specification;

import java.util.stream.Collectors;

public class TransferObj {

    public static ProductDto fromProduct(Product p) {
        var specifications = p.getSpecification()
                .stream().map(TransferObj::fromSpecification)
                .collect(Collectors.toList());
        var comments = p.getComments()
                .stream().map(TransferObj::fromComment)
                .collect(Collectors.toList());
        return new ProductDto(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getQuantity(),
                p.getDescription(),
                p.getPreviewImage(),
                p.getImages(),
                specifications,
                comments,
                p.getCategory().getName()
        );
    }

    public static SpecificationDto fromSpecification(Specification s) {
        return new SpecificationDto(s.getId(), s.getName(), s.getDescription());
    }

    public static CommentDto fromComment(Comment c) {
        return new CommentDto(c.getId(), c.getName(), c.getComment());
    }

    public static CategoryDto fromCategory(Category c) {
        return new CategoryDto(c.getId(), c.getName());
    }

    public static Comment toComment(CommentDto c) {
        return new Comment(
                c.getId(),
                c.getName(),
                c.getDescription()
        );
    }

}
