package com.bulish.jennyshop.entity;

import org.springframework.data.jpa.domain.Specification;

/**
 * Class is responsible for filtering data using specifications
 * @author Bulish Evgenia
 * @version 1.0
 */
public class ProductSpecification {

    public static Specification<Product> productNamePrefix(String prefix){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), prefix + "%");
    }

    public static Specification<Product> minCost(Integer min){
        return (root, query, criteriaBuilder) -> criteriaBuilder.ge(root.get("cost"), min);
    }
    public static Specification<Product> maxCost(Integer max){
        return (root, query, criteriaBuilder) -> criteriaBuilder.le(root.get("cost"), max);
    }
}
