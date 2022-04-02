package ru.khorolskii.shop.carts.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.khorolskii.shop.api.CartDto;
import ru.khorolskii.shop.carts.model.Cart;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto cartToConverterCartDto(Cart cart){
      CartDto cartDto = new CartDto();
      cartDto.setTotalPrice(cart.getTotalPrice());
      cartDto.setItems(cart.getItems().stream().map(cartItemConverter:: cartItemToConverterItemDto).collect(Collectors.toList()));
      return cartDto;
    }

}
