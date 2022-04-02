package ru.khorolskii.shop.core.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.khorolskii.shop.core.entities.Category;
import ru.khorolskii.shop.core.repositories.CategoryRepository;

import java.util.Optional;



@Slf4j
@Service
@Data
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional <Category> findByTitle(String title){
        return categoryRepository.findByTitle(title);
    }
}
