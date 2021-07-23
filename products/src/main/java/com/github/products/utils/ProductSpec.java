package com.github.products.utils;

import com.github.products.entity.Criteria;
import com.github.products.entity.EntityStatus;
import com.github.products.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpec {

    private ProductSpec() {
    }

    public static Specification<Product> statusOn() {
        return (root, query, cb) -> cb.equal(root.get("status"), EntityStatus.on);
    }

    public static Specification<Product> statusOff() {
        return (root, query, cb) -> cb.equal(root.get("status"), EntityStatus.off);
    }

    public static Specification<Product> bySubcategory(String subcategory) {
        return ((root, query, cb) -> cb.equal(root.get("subcategory").get("name"), subcategory));
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
