package ru.khorolskii.shop.carts.converters;

import org.springframework.stereotype.Component;
import ru.khorolskii.shop.api.CartItemDto;
import ru.khorolskii.shop.carts.model.CartItem;

@Component
public class CartItemConverter {

    public CartItemDto cartItemToConverterItemDto(CartItem cartItem){
      CartItemDto cartItemDto = new CartItemDto();
      cartItemDto.setProductId(cartItem.getProductId());
      cartItemDto.setProductTitle(cartItem.getProductTitle());
      cartItemDto.setQuantity(cartItem.getQuantity());
      cartItemDto.setPrice(cartItem.getPrice());
      cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
      return cartItemDto;
    }

}
