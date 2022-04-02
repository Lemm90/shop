package ru.khorolskii.shop.core.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.khorolskii.shop.api.ProductDto;
import ru.khorolskii.shop.api.ProductToCartDto;
import ru.khorolskii.shop.core.entities.Category;
import ru.khorolskii.shop.core.entities.Product;
import ru.khorolskii.shop.core.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final CategoryService categoryService;


    public Product productDtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        return product;
    }

    public ProductDto productToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());
        productDto.setCategoryTitle(product.getCategory().getTitle());
        return productDto;
    }

    public ProductToCartDto productToCartDto(Product product){
        ProductToCartDto productToCartDto = new ProductToCartDto();
        productToCartDto.setId(product.getId());
        productToCartDto.setPrice(product.getPrice());
        productToCartDto.setTitle(product.getTitle());
        return productToCartDto;
    }

}
