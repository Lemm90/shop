package ru.khorolskii.shop.core.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.khorolskii.shop.core.entities.Product;

public class ProductsSpecifications {

    public static Specification <Product> priceGreaterOrEqualsThan(Integer minPrice){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification <Product> pricelessThanOrEqualThan(Integer maxPrice){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification <Product> titleLike (String title){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", title));
    }

}
