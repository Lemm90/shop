package ru.khorolskii.shop.carts.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.khorolskii.shop.api.CartDto;
import ru.khorolskii.shop.api.ProductDto;
import ru.khorolskii.shop.carts.converters.CartConverter;
import ru.khorolskii.shop.carts.services.CartService;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
       cartService.add(id);
    }

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.cartToConverterCartDto(cartService.getCurrentCart());
    }

    @GetMapping("/remove")
    public void removeCart(Long productId) {
        cartService.removeCart(productId);
    }

    @GetMapping("/clear/")
    public void clearCart() {
        cartService.clearCart();
    }

    @GetMapping("/change_quantity")
    public CartDto changeQuantity(@RequestParam Long productId, @RequestParam Integer delta){
        return cartConverter.cartToConverterCartDto(cartService.changeQuantity(productId, delta));
    }
}
