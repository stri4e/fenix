package com.github.products.utils;

import com.github.products.entity.Criteria;
import com.github.products.entity.Product;
import com.github.products.entity.EntityStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSpec {

    public static Specification<Product> statusUsed() {
        return (root, query, cb) -> cb.equal(root.get("status"), EntityStatus.on);
    }

    public static Specification<Product> statusUnUsed() {
        return (root, query, cb) -> cb.equal(root.get("status"), EntityStatus.off);
    }

    public static Specification<Product> subcategory(String category) {
        return ((root, query, cb) -> cb.equal(root.get("subcategory").get("name"), category));
    }

    public static Specification<Product> selectCriteriaIn(String subcategory, List<Criteria> criteria) {
        return ((root, query, cb) -> {
            query.distinct(Boolean.TRUE);
            return cb.and(
                    cb.equal(root.get("subcategory").get("name"), subcategory),
                    root.join("criteria").in(criteria)
            );
        });
    }

}
