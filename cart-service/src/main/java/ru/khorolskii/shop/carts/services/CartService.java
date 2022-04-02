package ru.khorolskii.shop.carts.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.khorolskii.shop.api.ProductDto;
import ru.khorolskii.shop.carts.integrations.ProductServiceIntegration;
import ru.khorolskii.shop.carts.model.Cart;


import javax.annotation.PostConstruct;


@Slf4j
@Service
@Data
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    @PostConstruct
    public void init(){
        tempCart = new Cart();
    }

    public Cart getCurrentCart(){
        return tempCart;
    }

    public void add (Long productId){
        ProductDto productDto = productServiceIntegration.getProductById(productId).orElseThrow(() -> new RuntimeException("Не удается добавить продукт"));
        tempCart.add(productDto);

    }

    public void removeCart(Long productId) {
        tempCart.remove(productId);
    }

    public void clearCart() {
        tempCart.clear();
    }

    public Cart changeQuantity(Long productId, Integer delta){
        tempCart.changeQuantity(productId, delta);
        return tempCart;
    }
}
