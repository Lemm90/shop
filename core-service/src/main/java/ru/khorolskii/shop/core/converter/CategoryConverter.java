package ru.khorolskii.shop.core.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.khorolskii.shop.api.CategoryDto;
import ru.khorolskii.shop.core.entities.Category;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

    private final ProductConverter productConverter;

    public CategoryDto categoryToCategoryDto (Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setProducts(category.getProducts().stream().map(productConverter::productToProductDto).collect(Collectors.toList()));
        return categoryDto;
    }

}
