package com.iotiq.application.service;

import com.iotiq.application.domain.Product;
import com.iotiq.application.domain.enums.Certification;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> isLike(String attribute, String value) {
        return (root, query, cb) -> value == null ? null : cb.like(cb.lower(root.get(attribute)), "%" + value.toLowerCase() + "%");
    }

    public static <E extends Enum<E>> Specification<Product> isIn(String attribute, String subAttribute, List<E> values) {
        return (root, query, cb) -> values == null ? null : root.get(attribute).get(subAttribute).in(values);
    }

    public static <E extends Enum<E>> Specification<Product> isIn(String attribute, List<E> values) {
        return (root, query, cb) -> values == null ? null : root.get(attribute).in(values);
    }

    public static <E extends Enum<E>> Specification<Product> isInList(String attribute, String subAttribute, List<E> values) {
        return (root, query, cb) -> {
            if (values == null) {
                return cb.conjunction();
            }
            Join<Product, Certification> join = root.join(attribute);
            Join<Certification, Certification> enumJoin = join.join(subAttribute);
            return enumJoin.in(values);
        };
    }

    public static <E extends Enum<E>> Specification<Product> isInList(String attribute, List<E> values) {
        return (root, query, cb) -> {
            if (values == null) {
                return cb.conjunction();
            }
            Join<Product, Certification> join = root.join(attribute);
            return join.in(values);
        };
    }


    public static Specification<Product> priceBetween(BigDecimal lowest, BigDecimal highest) {
        if(lowest == null && highest == null) {
            return null;
        }
        if( lowest != null && highest == null ) {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price").get("amount"), lowest);
        }
        if( lowest == null) {
            return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price").get("amount"), highest);
        }
        return (root, query, cb) -> cb.between(root.get("price").get("amount"), lowest, highest);
    }

}
